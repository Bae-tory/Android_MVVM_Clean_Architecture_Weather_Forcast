package com.sungjae.weather_forcast.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.sungjae.weather_forcast.BR
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


/**
 * @author Sungjae Bae
 * @param B  : DataBinding Type
 * @param VM : MVVM ViewModel Instance
 * @constructor layoutResId : layout resource id formatted .xml
 * @see BaseViewModel to know more VM
 */
abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    protected abstract val vm: VM
    protected lateinit var binding: B
        private set

    /*
    * convention about activity lifecycle methods to be implemented for MVVM
    */
    abstract fun B.doInitBinding()
    abstract fun B.doOnCreateAfterInitBinding()
    abstract fun B.doOnResume()
    abstract fun B.doOnStart()
    abstract fun B.doJustBeforeStop()
    abstract fun B.doOnRestart()
    abstract fun B.doJustBeforeDestroy()
    abstract fun B.doOnBackPressed()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        with(binding) {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, vm)
            doInitBinding()
        }
        binding.doOnCreateAfterInitBinding() // call after binding instance allocated
    }

    override fun onResume() {
        super.onResume()
        binding.doOnResume()
    }

    override fun onStart() {
        super.onStart()
        binding.doOnStart()
    }

    override fun onStop() {
        // for safe
        binding.doJustBeforeStop()
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        binding.doOnRestart()
    }

    override fun onDestroy() {
        // for safe
        binding.doJustBeforeDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        binding.doOnBackPressed()
        super.onBackPressed()
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    protected fun <T> LiveData<T>.observe(observeInvoke: (T) -> Unit) {
        observe(this@BaseActivity, {
            observeInvoke(it)
        })
    }

    /**
     * @param action : lambda done by allocated binding instance defined by layoutResId
     * @see layoutResId : Activity setContentView layout resource id
     */
    protected inline fun bind(action: B.() -> Unit) {
        binding.run(action)
    }
}