package id.taufiq.bwamov

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.bwamov.boarding.OnBoardingOneActivity
import id.taufiq.bwamov.utils.delayFunction
import id.taufiq.bwamov.utils.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delayFunction({ moveToOnBoard() }, 3_000)
    }

    private fun moveToOnBoard() {
        startActivity(OnBoardingOneActivity::class.java)
        finish()

    }


}