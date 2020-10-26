package id.taufiq.bwamov.checkout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.bwamov.R
import id.taufiq.bwamov.model.Film
import id.taufiq.bwamov.utils.showToast
import kotlinx.android.synthetic.main.pilih_bangku_activity.*

class PilihBangkuActivity : AppCompatActivity() {


    var totalPembelian = 0
    var statusA1 = false
    var statusA2 = false
    var statusA3 = false
    var statusA4 = false
    var statusB1 = false
    var statusB2 = false
    var statusB3 = false
    var statusB4 = false

    private val price = ArrayList<CheckOut>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pilih_bangku_activity)

        val view = listOf(
            pb_a1, pb_a2, pb_a3, pb_a4, pb_b1, pb_b2, pb_b3, pb_b4
        )

        val data = intent.getParcelableExtra<Film>("data")
        pb_judul.text = data?.judul.toString()

        for (myView in view) {
            myView.setOnClickListener {
                makeSelected(it)
            }


        }

        btn_beli_tiket.setOnClickListener {
            showToast("Tiket dibeli!")
        }


    }

    private fun makeSelected(itemView: View) {
        when (itemView.id) {
            R.id.pb_a1 -> {
                if (statusA1) {
                    pb_a1.setImageResource(R.drawable.empty_bangku_state)
                    statusA1 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)
                } else {
                    pb_a1.setImageResource(R.drawable.selected_bangku_state)
                    statusA1 = true
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    val priceSold = CheckOut("A1", "50_000")
                    price.add(priceSold)

                }

            }

            R.id.pb_a2 -> {
                if (statusA2) {
                    pb_a2.setImageResource(R.drawable.empty_bangku_state)
                    statusA2 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)
                } else {
                    pb_a2.setImageResource(R.drawable.selected_bangku_state)
                    statusA2 = true
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    val priceSold = CheckOut("A2", "50_000")
                    price.add(priceSold)
                }


            }

            R.id.pb_a3 -> {

                if (statusA3) {
                    pb_a3.setImageResource(R.drawable.empty_bangku_state)
                    statusA3 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)
                } else {
                    pb_a3.setImageResource(R.drawable.selected_bangku_state)
                    statusA3 = true
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    val priceSold = CheckOut("A3", "50_000")
                    price.add(priceSold)
                }
            }

            R.id.pb_a4 -> {
                if (statusA4) {
                    pb_a4.setImageResource(R.drawable.empty_bangku_state)
                    statusA4 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)
                } else {
                    pb_a4.setImageResource(R.drawable.selected_bangku_state)
                    statusA4 = true
                    val priceSold = CheckOut("A4", "50_000")
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    price.add(priceSold)

                }
            }

            R.id.pb_b1 -> {
                if (statusB1) {
                    pb_b1.setImageResource(R.drawable.empty_bangku_state)
                    statusB1 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)

                } else {
                    pb_b1.setImageResource(R.drawable.selected_bangku_state)
                    statusB1 = true
                    val priceSold = CheckOut("B1", "50_000")
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    price.add(priceSold)
                }
            }


            R.id.pb_b2 -> {

                if (statusB2) {

                    pb_b2.setImageResource(R.drawable.empty_bangku_state)
                    statusB2 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)
                } else {
                    pb_b2.setImageResource(R.drawable.selected_bangku_state)
                    statusB2 = true
                    val priceSold = CheckOut("B2", "50_000")
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    price.add(priceSold)
                }
            }


            R.id.pb_b3 -> {
                if (statusB3) {
                    pb_b3.setImageResource(R.drawable.empty_bangku_state)
                    statusB3 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)
                } else {
                    pb_b3.setImageResource(R.drawable.selected_bangku_state)
                    statusB3 = true
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    val priceSold = CheckOut("B3", "50_000")
                    price.add(priceSold)
                }
            }


            R.id.pb_b4 -> {
                if (statusB4) {
                    pb_b4.setImageResource(R.drawable.empty_bangku_state)
                    statusB4 = false
                    totalPembelian -= 1
                    beliTiket(totalPembelian)
                } else {
                    pb_b4.setImageResource(R.drawable.selected_bangku_state)
                    statusB4 = true
                    totalPembelian += 1
                    beliTiket(totalPembelian)
                    val priceSold = CheckOut("B4", "50_000")
                    price.add(priceSold)
                }
            }
        }
    }

    private fun beliTiket(totalPembelian: Int) = if (totalPembelian == 0) {
        btn_beli_tiket.text = "Beli Tiket"
        btn_beli_tiket.visibility = View.INVISIBLE

    } else {
        btn_beli_tiket.visibility = View.VISIBLE
        btn_beli_tiket.text = "Beli Tiket ($totalPembelian)"
    }
}