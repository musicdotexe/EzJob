package ezjob.ghn.com.ezjob.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Session(


        @SerializedName("password")
        val password: String? = null,

        @SerializedName("name")
        val name: String? = null,

        @SerializedName("sessionToken")
        val sessionToken: String? = null,

        @SerializedName("id")
        val id: Int? = null,

        @SerializedName("deviceId")
        val deviceId: String? = null,

        @SerializedName("status")
        val status: Int? = null,

        @SerializedName("username")
        val username: String? = null
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelSession.CREATOR
    }

}