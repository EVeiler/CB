package and5.kamil.challenge_ch5.model

import com.google.gson.annotations.SerializedName

data class PostUserRespond(
    @SerializedName("address")
    val address: String,
    @SerializedName("complete_name")
    val completeName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("dateofbirth")
    val dateofbirth: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)
