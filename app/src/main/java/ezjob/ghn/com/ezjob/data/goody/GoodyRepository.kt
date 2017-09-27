package ezjob.ghn.com.ezjob.data.goody

import ezjob.ghn.com.ezjob.data.model.Session

/**
 * Created by Van T Tran on 08-Aug-17.
 */
class GoodyRepository(val apiService: GoodyService) {
    fun getSession(deviceId: String): io.reactivex.Single<Session> {
        return apiService.getSession(deviceId = deviceId)
    }
}