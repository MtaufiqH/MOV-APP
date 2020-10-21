package id.taufiq.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.taufiq.bwamov.R
import id.taufiq.bwamov.model.Play
import kotlinx.android.synthetic.main.who_played_row.view.*

/**
 * Created By Taufiq on 10/21/2020.
 *
 */
class WhoPlayedAdapter(private val list: List<Play>, private val listener: (Play) -> Unit) :
    RecyclerView.Adapter<WhoPlayedAdapter.WhoPlayedViewHolder>() {

    private lateinit var context: Context


    class WhoPlayedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(play: Play, listener: (Play) -> Unit, contextAdapter: Context) {


            Glide.with(contextAdapter)
                .load(play.url)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.whos_played)

            itemView.whos_played_name.text = play.nama


            itemView.setOnClickListener {
                listener(play)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhoPlayedViewHolder {
        context = parent.context

        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.who_played_row, parent, false)

        return WhoPlayedViewHolder(view)
    }

    override fun onBindViewHolder(holder: WhoPlayedViewHolder, position: Int) {
        holder.bindItem(list[position], listener, context)
    }

    override fun getItemCount(): Int = list.size


}