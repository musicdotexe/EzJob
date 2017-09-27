package ezjob.ghn.com.ezjob.screens.welcome

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import ezjob.ghn.com.ezjob.R
import ezjob.ghn.com.ezjob.base.BaseLifecycleActivity
import ezjob.ghn.com.ezjob.data.model.ErrorResponse
import ezjob.ghn.com.ezjob.data.model.Session
import ezjob.ghn.com.ezjob.data.model.Session.SessionViewModel
import ezjob.ghn.com.ezjob.utils.Constants
import ezjob.ghn.com.ezjob.utils.DialogFactory

class WelcomeActivity : BaseLifecycleActivity<SessionViewModel>() {

    override val viewModelClass = SessionViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.isLoadingLiveData.observe(this, Observer<Boolean> {
            //            it?.let { lRefresh.isRefreshing = it }
        })
        viewModel.resultLiveData.observe(this, Observer<Pair<Session, Throwable>> {
            it?.let {
                //TODO add
                Toast.makeText(this, it.first.name, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.throwableLiveData.observe(this, Observer<Throwable> {
            it?.let {
                if (it is ErrorResponse)
                    when (it.error) {
                        Constants.ERR_SESSION_NOT_VALID -> {
                            DialogFactory.createSimpleOkErrorDialog(
                                    this,
                                    "",
                                    getString(R.string.error_session_invalid).replace("_d", viewModel.resultLiveData.deviceId!!),
                                    getString(R.string.dialog_try_again),
                                    {
                                        viewModel.callAgain()
                                    }).show()
                        }

                    }
            }
        })
    }

}
