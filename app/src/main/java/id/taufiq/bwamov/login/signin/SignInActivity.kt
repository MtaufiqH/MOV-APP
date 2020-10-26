package id.taufiq.bwamov.login.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import id.taufiq.bwamov.R
import id.taufiq.bwamov.home.HomeActivity
import id.taufiq.bwamov.login.signup.SignUpActivity
import id.taufiq.bwamov.model.Users
import id.taufiq.bwamov.utils.MyPreferences
import id.taufiq.bwamov.utils.showToast
import id.taufiq.bwamov.utils.startActivity
import kotlinx.android.synthetic.main.sign_in_activity.*


class SignInActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    lateinit var prefs: MyPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        database = FirebaseDatabase.getInstance().getReference("User")
        prefs = MyPreferences(this)


        // SET PREFERENCE BOARDING to 1
        prefs.setValue("BOARDING_STATUS", "1")

        // Jika telah login sebelumnya, maka akan langsung ke home.
        if (prefs.getValue("STATUS").equals("1")) {
            finishAffinity()
            startActivity(HomeActivity::class.java)
        }

        btn_masuk_akun.setOnClickListener {
            val username = et_username_signin.text.toString()
            val password = et_password_signin.text.toString()

            if (username.equals("")) {
                et_username_signin.error = "Masukkan username anda"
                et_username_signin.requestFocus()
            } else if (password.equals("")) {
                et_password_signin.error = "Masukkan password anda"
                et_password_signin.requestFocus()

            } else {
                pushLogin(username, password)
            }

        }


        btn_daftar_akun.setOnClickListener {
            startActivity(SignUpActivity::class.java)
        }


    }

    fun pushLogin(username: String, password: String) {
        database.child(username).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(Users::class.java)
                if (user == null) {
                    showToast("User tidak ditemukan")
                } else {
                    if (user.password.equals(password)) {
                        prefs.apply {

                            setValue("NAME", user.nama.toString())
                            setValue("EMAIL", user.email.toString())
                            setValue("PASSWORD", user.password.toString())
                            setValue("SALDO", user.saldo.toString())
                            setValue("URL", user.url.toString())
                            setValue("USERNAME", user.username.toString())
                            setValue("STATUS", "1")

                        }


                        startActivity(HomeActivity::class.java)
                    } else {
                        showToast("Password anda salah")
                    }

                }
            }

            override fun onCancelled(messageError: DatabaseError) {
                showToast(messageError.message)
            }
        })
    }
}