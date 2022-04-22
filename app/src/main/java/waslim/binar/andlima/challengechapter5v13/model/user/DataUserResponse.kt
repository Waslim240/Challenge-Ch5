package waslim.binar.andlima.challengechapter5v13.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataUserResponse(
        @SerializedName("address")
        val address: String,
        @SerializedName("complete_name")
        val completeName: String,
        @SerializedName("dateofbirth")
        val dateofbirth: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("username")
        val username: String,
        @SerializedName("id")
        val id: String
) : Parcelable
