package ezjob.ghn.com.ezjob.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import java.io.IOException
import android.widget.TextView
import ezjob.ghn.com.ezjob.data.model.ErrorResponse
import com.google.gson.Gson
import ezjob.ghn.com.ezjob.R
import ezjob.ghn.com.ezjob.data.AdvertisingIdClient


/**
 * Created by Van T Tran on 08-Aug-17.
 */

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)


fun retrieveAdsInfo(context: Context): String {
    var adInfo: AdvertisingIdClient.AdInfo? = null

    try {
        adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
        return adInfo!!.getId()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return ""
}

object Constants {
    val RESPONSE_OK = 1
    val ERR_SESSION_TIMEOUT = 440
    val ERR_INTERNET_DISCONNECTED = 441
    val ERR_UNKNOWN = 443
    val ERR_SESSION_NOT_VALID = 200
}

object Singleton {
////    val moshi : Moshi by lazy { Moshi.Builder()
////            .add(KotlinJsonAdapterFactory())
////            .build() }
//    //    errorAdapter = JsonAdapter<>()
////    private val errorAdapter: JsonAdapter<ErrorResponse> by lazy {
////        moshi.adapter(ErrorResponse::class.java)
////    }
//
//    inline fun <reified T> convertToJson(data: T): String {
//        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
//
//        return jsonAdapter.toJson(data)
//    }
//
//    fun <T> convertFromJson(jsonStr: String, dataClass: Class<T>): T? {
//        val jsonAdapter: JsonAdapter<T> = moshi.adapter(dataClass)
//
//        return jsonAdapter.fromJson(jsonStr)
//    }

    fun parseError(jsonStr: String): ErrorResponse? {
//        val moshi = Moshi.Builder()
//                .add(KotlinJsonAdapterFactory())
//                .build()
//        var adapter = moshi.adapter(ErrorResponse::class.java)
//        return adapter.fromJson(jsonStr)
        val gson = Gson()
        return gson.fromJson(jsonStr, ErrorResponse::class.java)
    }

//    fun getGson(): Gson {
//        var gson = GsonBuilder()
//                .registerTypeAdapterFactory(AutoValueGsonFactory.create())
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//                .create()
//        return gson
//    }

}

object DialogFactory {
    fun createSimpleOkErrorDialog(context: Context, title: String, message: String, buttonTextResId: String, callback: () -> Unit): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_confirm_layout)

        dialog.findViewById<TextView>(R.id.confirm_dialog_message).text = message
        val tvCancel = dialog.findViewById<TextView>(R.id.dialog_confirm_negative_btn) as TextView
        tvCancel.visibility = View.GONE
        dialog.findViewById<View>(R.id.confirm_dialog_divider).setVisibility(View.INVISIBLE)
        val tvDelete = dialog.findViewById<TextView>(R.id.dialog_confirm_positive_btn) as TextView
        tvDelete.text = buttonTextResId
        tvDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                dialog.dismiss()
                callback()
            }
        })
        return dialog
    }
}