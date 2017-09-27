package ezjob.ghn.com.ezjob.screens.search

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import ezjob.ghn.com.ezjob.R
import ezjob.ghn.com.ezjob.base.BaseLifecycleActivity
import ezjob.ghn.com.ezjob.data.model.UserSample.UserAdapter
import ezjob.ghn.com.ezjob.data.model.UserSample.UserViewModel
import ezjob.ghn.com.ezjob.data.sample.Result

import kotlinx.android.synthetic.main.activity_repos.*

class MainActivity : BaseLifecycleActivity<UserViewModel>() {

    override val viewModelClass = UserViewModel::class.java


    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        rv.setHasFixedSize(true)
        rv.adapter = adapter


        if (savedInstanceState == null)
            viewModel.setLocation("Lagos")
        observeLiveData();
    }

    private fun observeLiveData() {
        viewModel.isLoadingLiveData.observe(this, Observer<Boolean> {
                        it?.let { lRefresh.isRefreshing = it }
        })
        viewModel.resultLiveData.observe(this, Observer<Pair<Result, Throwable>> {
                        it?.let { adapter.dataSource = it?.first?.items }
        })
        viewModel.throwableLiveData.observe(this, Observer<Throwable> {
                        it?.let { Snackbar.make(rv, it.localizedMessage, Snackbar.LENGTH_LONG).show() }
        })
    }


//    private fun observerLiveData(){
//        viewModel.isLoadingLiveData.observe(this, Observer<Boolean> {
//
//        })
//    }


}
