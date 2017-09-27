package ezjob.ghn.com.ezjob.data.goody

import android.content.Context

/**
 * Created by Van T Tran on 08-Aug-17.
 */
object GoodyRepositoryProvider {
    fun provideGoodyRepository(context : Context): GoodyRepository {
        return GoodyRepository(GoodyService.create(context))
    }
}