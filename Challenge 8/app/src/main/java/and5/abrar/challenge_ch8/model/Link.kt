package and5.abrar.challenge_ch8.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link(
    @SerializedName("endpoint")
    val endpoint: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("url")
    val url: String
): Parcelable