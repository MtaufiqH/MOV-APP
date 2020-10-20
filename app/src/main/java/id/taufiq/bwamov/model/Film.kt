package id.taufiq.bwamov.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created By Taufiq on 10/20/2020.
 *
 */

@Parcelize
data class Film(
    var desc: String? = "",
    var director: String? = "",
    var genre: String? = "",
    var judul: String?= "",
    var poster: String? = "",
    var rating: String? = "",
) : Parcelable