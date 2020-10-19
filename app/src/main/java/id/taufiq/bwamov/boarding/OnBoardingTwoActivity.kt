package id.taufiq.bwamov.boarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.bwamov.R
import id.taufiq.bwamov.utils.startActivity
import kotlinx.android.synthetic.main.activity_on_boarding_two.*

class OnBoardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)


        btn_lanjut.setOnClickListener {
            startActivity(OnBoardingThreeActivity::class.java)
        }
    }
}