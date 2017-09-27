package ezjob.ghn.com.ezjob.data.model.UserSample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import ezjob.ghn.com.ezjob.data.sample.Result

/**
 * Created by Van T Tran on 08-Aug-17.
 */
open class UserViewModel(application: Application?) : AndroidViewModel(application){
    private val locationLiveData = MutableLiveData<String>()

    val resultLiveData = UserLiveData().apply {
        this.addSource(locationLiveData){
            it?.let { this.location = it }
        }
    }
    val isLoadingLiveData = MediatorLiveData<Boolean>().apply {
        this.addSource(resultLiveData){
            this.value = false
        }
    }
    val throwableLiveData = MediatorLiveData<Throwable>().apply {
        this.addSource(resultLiveData){
            it?.second?.let {
                this.value = it
            }
        }
    }
    val userLiveData = MediatorLiveData<Result>().apply {
        this.addSource(resultLiveData){
            it?.first?.let {
                this.value = it
            }
        }
    }

    fun setLocation(location : String ){
        locationLiveData.value = location
        isLoadingLiveData.value = true
    }
}