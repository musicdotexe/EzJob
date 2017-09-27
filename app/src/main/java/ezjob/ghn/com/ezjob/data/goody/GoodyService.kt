package ezjob.ghn.com.ezjob.data.goody

import android.content.Context
import ezjob.ghn.com.ezjob.data.model.Session
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Van T Tran on 08-Aug-17.
 */

interface GoodyService {

    @GET("checker_api/user/get_session")
    fun getSession(@Query("device_id") deviceId: String): io.reactivex.Single<Session>


    companion object Factory {
        fun create(context : Context): GoodyService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val errorInterceptor = ConnectivityInterceptor(context)
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(errorInterceptor)
                    .build()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("http://staging.goodyapp.vn")
                    .client(client)
                    .build()
            return retrofit.create(GoodyService::class.java)
        }
    }
}