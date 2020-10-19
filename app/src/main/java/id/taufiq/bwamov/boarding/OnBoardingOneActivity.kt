package id.taufiq.bwamov.boarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.bwamov.R
import id.taufiq.bwamov.login.signin.SignInActivity
import id.taufiq.bwamov.utils.Preferences
import id.taufiq.bwamov.utils.startActivity
import kotlinx.android.synthetic.main.activity_on_boarding_one.*

class OnBoardingOneActivity : AppCompatActivity() {

    lateinit var prefs: Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        // set prefs to 1
        prefs = Preferences(this)

        if (prefs.getValue("BOARDING_STATUS").equals("1")) { startActivity(SignInActivity::class.java)}

        btn_lanjut.setOnClickListener { startActivity(OnBoardingTwoActivity::class.java) }

        btn_lewat.setOnClickListener {

            prefs.setValue("BOARDING_STATUS", "1")
            finishAffinity()
            startActivity(SignInActivity::class.java)

        }
    }
}