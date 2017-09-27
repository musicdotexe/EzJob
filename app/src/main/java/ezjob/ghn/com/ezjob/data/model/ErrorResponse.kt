package ezjob.ghn.com.ezjob.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.net.ConnectException

/**
 * Created by Van T Tran on 09-Aug-17.
 */

@PaperParcel
data class ErrorResponse(@SerializedName("errorCode") val error : Int,
                         @SerializedName("errorMessage") override val message: String? ) : PaperParcelable, Exception() {
    companion object {
        @JvmField val CREATOR = PaperParcelSession.CREATOR
    }
}