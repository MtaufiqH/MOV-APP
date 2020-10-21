package id.taufiq.bwamov.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import id.taufiq.bwamov.DetailMovieActivity
import id.taufiq.bwamov.R
import id.taufiq.bwamov.adapter.ComingSoonAdapter
import id.taufiq.bwamov.adapter.NowPlayingAdapter
import id.taufiq.bwamov.model.Film
import id.taufiq.bwamov.utils.MyPreferences
import id.taufiq.bwamov.utils.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {


    lateinit var prefs: MyPreferences
    lateinit var mDatabase: DatabaseReference

    var listOfData = ArrayList<Film>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        prefs = context?.applicationContext?.let { MyPreferences(it) }!!

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tv_name_user.text = prefs.getValue("NAME")
        if (prefs.getValue("SALDO").equals("")) {
            prefs.getValue("SALDO")?.toDouble()?.let {
                currency(it, wallet_user)
            }
        }

        Glide.with(this)
            .load(prefs.getValue("URL"))
            .apply(RequestOptions.circleCropTransform())
            .into(photo_user)


        rv_now_playing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_coming_soon.layoutManager =
            LinearLayoutManager(context)

        getData()

    }


    //get data
    private fun getData() {

        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listOfData.clear()

                for (data in dataSnapshot.children) {
                    val film = data?.getValue(Film::class.java)
                    listOfData.add(film!!)
                }

                rv_now_playing.adapter = NowPlayingAdapter(listOfData) {
                    val intentDetailNowPlaying = Intent(context,DetailMovieActivity::class.java)
                        .putExtra("MOVIES_DATA", it)
                    startActivity(intentDetailNowPlaying)

                }

                rv_coming_soon.adapter = ComingSoonAdapter(listOfData){

                    val intentDetailComingSoon = Intent(context,DetailMovieActivity::class.java)
                        .putExtra("MOVIES_DATA", it)
                    startActivity(intentDetailComingSoon)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                context?.showToast(error.toString())
            }

        })
    }

    // change format currency
    private fun currency(harga: Double, walletUser: TextView?) {
        val localeId = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeId)
        walletUser?.text = format.format(harga)
    }

}