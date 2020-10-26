package id.taufiq.bwamov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import id.taufiq.bwamov.adapter.WhoPlayedAdapter
import id.taufiq.bwamov.checkout.PilihBangkuActivity
import id.taufiq.bwamov.model.Film
import id.taufiq.bwamov.model.Play
import id.taufiq.bwamov.utils.showToast
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    private val dataList = ArrayList<Play>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val data = intent.getParcelableExtra<Film>("MOVIES_DATA")

        database = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString()).child("play")


        title_detail.text = data?.judul
        genre_detail.text = data?.genre
        description_detail.text = data?.desc
        tv_rating_details.text = data?.rating

        Glide.with(this).load(data?.poster)
            .into(poster_header_detail)

        rv_who_played.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        getPlayedData()

        btn_pilih_bangku.setOnClickListener {
            val toPilihBangkuActivity = Intent(this, PilihBangkuActivity::class.java)
                .putExtra("data", data)

            startActivity(toPilihBangkuActivity)
        }

    }

    private fun getPlayedData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for (snapshotData in snapshot.children) {
                    val whoPlayed = snapshotData.getValue(Play::class.java)
                    dataList.add(whoPlayed!!)
                }

                rv_who_played.adapter = WhoPlayedAdapter(dataList) {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }

        })
    }
}