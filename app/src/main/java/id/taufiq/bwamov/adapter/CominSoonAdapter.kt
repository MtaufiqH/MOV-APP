package id.taufiq.bwamov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.taufiq.bwamov.R
import id.taufiq.bwamov.model.Film

/**
 * Created By Taufiq on 10/20/2020.
 *
 */
class ComingSoonAdapter(dataList: ArrayList<Film>, listener: (Film) -> Unit) : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.coming_soon_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}