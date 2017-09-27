package ezjob.ghn.com.ezjob.data.sample

/**
 * Created by Van T Tran on 08-Aug-17.
 */
class GithubRepository(val apiService: GithubApiService) {
    fun searchUsers(location: String, language: String): io.reactivex.Single<Result> {
        return apiService.search(query = "deviceId:$location language:$language")
    }
}