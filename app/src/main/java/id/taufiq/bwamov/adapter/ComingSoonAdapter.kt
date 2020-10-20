package id.taufiq.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.taufiq.bwamov.R
import id.taufiq.bwamov.model.Film

/**
 * Created By Taufiq on 10/20/2020.
 *
 */
class ComingSoonAdapter(
    private var dataList: List<Film>,
    private val listener: (Film) -> Unit
) : RecyclerView.Adapter<ComingSoonAdapter.ComingViewHolder>() {

    lateinit var contextAdapter: Context


    class ComingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val movieTitle = view.findViewById<TextView>(R.id.tv_judul_movie)
        private val movieGenre = view.findViewById<TextView>(R.id.tv_genre_movie)
        private val moviePoster = view.findViewById<ImageView>(R.id.iv_poster_movie)

        fun bindItem(data: Film, listener: (Film) -> Unit, context: Context) {

            movieTitle.text = data.judul
            movieGenre.text = data.genre

            Glide.with(context)
                .load(data.poster)
                .into(moviePoster)



            itemView.setOnClickListener {
                listener(data)
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingViewHolder {
        contextAdapter = parent.context

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.coming_soon_row, parent, false)

        return ComingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComingViewHolder, position: Int) {
        holder.bindItem(dataList[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = dataList.size

}

