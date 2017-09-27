package ezjob.ghn.com.ezjob.data.model.Session

import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import android.util.Log
import ezjob.ghn.com.ezjob.data.model.Session
import ezjob.ghn.com.ezjob.data.goody.GoodyRepositoryProvider
import ezjob.ghn.com.ezjob.utils.retrieveAdsInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Van T Tran on 08-Aug-17.
 */
class SessionLiveData(val context: Context) : MediatorLiveData<Pair<Session, Throwable>>() {

    private var disposal: Disposable? = null

    init {
        getDeviceId()
    }

    var deviceId: String? = null
        set(value) {
            field = value
            value?.let {
                getSession(it)
            }
        }

    fun getSession(deviceId: String) {
        disposal = GoodyRepositoryProvider
                .provideGoodyRepository(context)
                .getSession(deviceId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { data, error ->
                    this@SessionLiveData.value = Pair(data, error)
                }
    }

    override fun onInactive() {
        super.onInactive()
        if (disposal?.isDisposed?.not() ?: false) {
            disposal?.dispose()
        }
    }

    fun getDeviceId() {
        disposal = Single.fromCallable {
            retrieveAdsInfo(context)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { deviceId ->
                            Log.d("DeviceId", "${deviceId}")
                            this@SessionLiveData.deviceId = deviceId
                        },
                        { error ->
                            // do something

                            Log.d("DeviceId", "${error}")
                            this@SessionLiveData.value = Pair(null!!, error)
                        }
                )
    }
}