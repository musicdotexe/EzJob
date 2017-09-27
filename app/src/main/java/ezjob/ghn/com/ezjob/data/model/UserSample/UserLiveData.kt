package ezjob.ghn.com.ezjob.data.model.UserSample

import android.arch.lifecycle.MediatorLiveData
import android.util.Log
import ezjob.ghn.com.ezjob.data.sample.GithubRepositoryProvider
import ezjob.ghn.com.ezjob.data.sample.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Van T Tran on 08-Aug-17.
 */
class UserLiveData : MediatorLiveData<Pair<Result,Throwable>>(){

    private var disposal : Disposable? = null

    var location : String? = null
    set(value) {
        value?.let {
            disposal = GithubRepositoryProvider
                    .provideGithubRepository()
                    .searchUsers(it,"Java")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe { data,error ->
                        Log.d("Result", "There are ${data.items.size} users at ${location} using JAVA")
                        this@UserLiveData.value = Pair(data,error)
                    }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (disposal?.isDisposed?.not() ?:false){
            disposal?.dispose()
        }
    }
}