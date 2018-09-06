package id.ergun.myfootballclub

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Club (val name: String?, val image: Int?, val description: String?) : Parcelable