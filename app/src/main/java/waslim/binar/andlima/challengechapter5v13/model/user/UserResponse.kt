package waslim.binar.andlima.challengechapter5v13.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("responseuser")
    val responseuser: DataUserResponse
) : Parcelable
