package com.listview.muvi.ui.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    val results : List<Movie>? = null
)

@Parcelize
data class Movie(
    var id : Int,

    var title: String,

    @SerializedName("overview")
    var description: String?,

    @SerializedName("poster_path")
    var image: String,

    @SerializedName("vote_average")
    var rating: Float,

    var runtime: Int,

    @SerializedName("release_date")
    var release: String?
) : Parcelable
