package com.sungjae.weather_forcast.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.sungjae.weather_forcast.BR


open class BaseViewHolder(parent: ViewGroup, @LayoutRes layoutResForViewType: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResForViewType, parent, false)),
    LifecycleOwner {

    private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    /**
     * @param item : datas to show
     * @param vm : ViewModel Instance for datas handling
     * @param eventHolder : Object already implemented about Event Listener
     *
     */
    open fun onBindViewHolder(
        item: Any?,
        vm: Any?,
        eventHolder: Any?
    ) {
        item?.let {
            with(binding) {
                lifecycleOwner = this@BaseViewHolder
                setVariable(BR.adapterPosition, adapterPosition)
                setVariable(BR.item, item)
                setVariable(BR.vm, vm)
                setVariable(BR.eventHolder, eventHolder)
                executePendingBindings()
            }
        }
    }

    /**
     * this is for LiveData
     * @see LiveData
     * @see Lifecycle.State
     */
    fun onAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    /**
     * this is for LiveData
     * @see LiveData
     * @see Lifecycle.State
     */
    fun onDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}