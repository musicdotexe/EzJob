package ezjob.ghn.com.ezjob.data.model.Session

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import ezjob.ghn.com.ezjob.data.model.Session

/**
 * Created by Van T Tran on 08-Aug-17.
 */
open class SessionViewModel(application: Application?) : AndroidViewModel(application) {
    val deviceIdLiveData = MutableLiveData<String>()

    val resultLiveData = SessionLiveData(application!!.applicationContext).apply {
        this.addSource(deviceIdLiveData) {
            it?.let { this.deviceId = it }
        }
    }
    val isLoadingLiveData = MediatorLiveData<Boolean>().apply {
        this.addSource(resultLiveData) {
            this.value = false
        }
    }
    val throwableLiveData = MediatorLiveData<Throwable>().apply {
        this.addSource(resultLiveData) {
            it?.second?.let {
                this.value = it
            }
        }
    }
    val sessionLiveData = MediatorLiveData<Session>().apply {
        this.addSource(resultLiveData) {
            it?.first?.let {
                this.value = it
            }
        }
    }

    fun callAgain() {
        if (resultLiveData.deviceId != null) {
            resultLiveData.getSession(resultLiveData.deviceId!!)
        } else {
            resultLiveData.getDeviceId()

        }
    }

}