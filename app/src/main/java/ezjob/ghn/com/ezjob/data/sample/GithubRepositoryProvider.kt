package ezjob.ghn.com.ezjob.data.sample

import ezjob.ghn.com.ezjob.data.sample.GithubApiService
import ezjob.ghn.com.ezjob.data.sample.GithubRepository

/**
 * Created by Van T Tran on 08-Aug-17.
 */
object GithubRepositoryProvider {
    fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubApiService.create())
    }
}