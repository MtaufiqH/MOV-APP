package id.taufiq.bwamov.home

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import id.taufiq.bwamov.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragment1 = HomeFragment()
        val fragment2 = TicketFragment()
        val fragment3 = UserFragment()

        changeFragment(fragment1)

        menu1.setOnClickListener {
            changeFragment(fragment1)
            changeStatusIcon(menu1, R.drawable.ic_home_active)
            changeStatusIcon(menu2, R.drawable.ic_tiket)
            changeStatusIcon(menu3, R.drawable.ic_profile)


        }
        menu2.setOnClickListener {
            changeFragment(fragment2)
            changeStatusIcon(menu1, R.drawable.ic_home)
            changeStatusIcon(menu2, R.drawable.ic_tiket_active)
            changeStatusIcon(menu3, R.drawable.ic_profile)
        }
        menu3.setOnClickListener {
            changeFragment(fragment3)

            changeStatusIcon(menu1, R.drawable.ic_home)
            changeStatusIcon(menu2, R.drawable.ic_tiket)
            changeStatusIcon(menu3, R.drawable.ic_profile_active)
        }
    }


    private fun changeFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()

    }

    private fun changeStatusIcon(image: ImageView, drawableImage: Int){
        image.setImageResource(drawableImage)
    }





    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        finish()
    }
}