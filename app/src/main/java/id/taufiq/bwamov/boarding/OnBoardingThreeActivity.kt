package id.taufiq.bwamov.boarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.bwamov.R
import id.taufiq.bwamov.login.signin.SignInActivity
import id.taufiq.bwamov.utils.startActivity
import kotlinx.android.synthetic.main.activity_on_boarding_three.*

class OnBoardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)


        btn_mulai.setOnClickListener {
            finishAffinity()
            startActivity(SignInActivity::class.java)
        }


    }
}