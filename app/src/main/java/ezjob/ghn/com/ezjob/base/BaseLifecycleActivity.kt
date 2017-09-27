package ezjob.ghn.com.ezjob.base

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import ezjob.ghn.com.ezjob.utils.unsafeLazy

/**
 * Created by Van T Tran on 08-Aug-17.
 */
abstract class BaseLifecycleActivity<T : AndroidViewModel> : AppCompatActivity(), LifecycleRegistryOwner {
    abstract val viewModelClass: Class<T>
    protected val viewModel: T by unsafeLazy { ViewModelProviders.of(this).get(viewModelClass) }
    private val registry = LifecycleRegistry(this)
    override fun getLifecycle(): LifecycleRegistry = registry
}