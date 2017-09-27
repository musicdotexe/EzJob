package ezjob.ghn.com.ezjob.data.sample

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Van T Tran on 08-Aug-17.
 */

interface GithubApiService{

    @GET("search/users")
    fun search(@Query("q") query: String,
            @Query("page") page : Int = 1,
            @Query("per_page") perPage : Int = 20) : io.reactivex.Single<Result>




    companion object Factory{
        fun create() : GithubApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("https://api.github.com")
                    .build()
            return retrofit.create(GithubApiService::class.java)
        }
    }
}