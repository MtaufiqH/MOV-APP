package id.taufiq.bwamov.login.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import id.taufiq.bwamov.R
import id.taufiq.bwamov.model.Users
import id.taufiq.bwamov.utils.showToast
import kotlinx.android.synthetic.main.sign_up_activity.*

class SignUpActivity : AppCompatActivity() {


    private lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)


        //setting firebase
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("User")

        btn_lanjutkan.setOnClickListener {

            val iUsername = et_input_username.text.toString()
            val iEmail = et_input_email.text.toString()
            val iPassword = et_input_password.text.toString()
            val iName = et_input_name.text.toString()

            when {
                iUsername == "" -> {
                    et_input_username.error = "Silahkan masukkan username"
                    et_input_username.requestFocus()
                }
                iEmail == "" -> {
                    et_input_email.error = "Silahkan masukkan email"
                    et_input_email.requestFocus()
                }
                iPassword == "" -> {
                    et_input_password.error = "Silahkan masukkan password"
                    et_input_password.requestFocus()
                }
                iName == "" -> {
                    et_input_name.error = "Silahkan masukkan nama"
                    et_input_name.requestFocus()
                }
                else -> {
                    saveUser(iUsername, iPassword, iName, iEmail)
                }
            }

        }

    }

    private fun saveUser(iUsername: String, iPassword: String, iName: String, iEmail: String) {
        val user = Users()
        user.apply {
            nama = iName
            email = iEmail
            password = iPassword
            username = iUsername

        }

        // checking username
        // jika username telah ada, maka show message
        if (user.username != null) {
            checkingUsername(iUsername, user)
        } else{
                showToast("Username telah digunakan")
        }


    }

    private fun checkingUsername(username: String, users: Users) {

        databaseReference.child(username).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapShot: DataSnapshot) {

                val user = dataSnapShot.getValue(Users::class.java)
                if (user == null) {
                    databaseReference.child(username).setValue(users)

                    val nextSignUp = Intent(this@SignUpActivity, SignUpPhotoActivity::class.java)
                        .putExtra("NAMA",user?.nama)
                    startActivity(nextSignUp)
                } else{
                    showToast("Username telah digunakan")
                }


            }

            override fun onCancelled(error: DatabaseError) {
                showToast("Error: ${error.message}")
            }
        })
    }
}