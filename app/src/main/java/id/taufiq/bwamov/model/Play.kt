package id.taufiq.bwamov.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created By Taufiq on 10/20/2020.
 *
 */

@Parcelize
data class Play(
    var nama: String? = "",
    var url: String? = "",
) : Parcelable