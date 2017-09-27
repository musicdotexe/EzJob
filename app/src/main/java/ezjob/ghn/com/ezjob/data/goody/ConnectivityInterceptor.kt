package ezjob.ghn.com.ezjob.data.goody

import android.content.Context
import android.util.Log
import ezjob.ghn.com.ezjob.data.model.ErrorResponse
import ezjob.ghn.com.ezjob.R
import ezjob.ghn.com.ezjob.utils.Constants
import ezjob.ghn.com.ezjob.utils.LiveNetworkMonitor
import ezjob.ghn.com.ezjob.utils.Singleton
import ezjob.ghn.com.ezjob.utils.unsafeLazy

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

/**
 * Created by Van T Tran on 09-Aug-17.
 */


class ConnectivityInterceptor(private val context: Context) : Interceptor {

    val networkMonitor: LiveNetworkMonitor by unsafeLazy { LiveNetworkMonitor(context) }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkMonitor.isConnected) {
            throw ErrorResponse(Constants.ERR_INTERNET_DISCONNECTED, context.resources.getString(R.string.error_disconnected))
        }
        val response: Response
        try {
            response = chain.proceed(chain.request())

            if (response.code() !== HttpURLConnection.HTTP_OK) {

                throw Singleton.parseError(response.body()?.string()!!)!!
            }
            return response
        } catch (e: Exception) {
            Log.d("API Err", "${e}")
            if (e is ErrorResponse)
                throw e
            else
                throw ErrorResponse(Constants.ERR_UNKNOWN, e.message)
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}