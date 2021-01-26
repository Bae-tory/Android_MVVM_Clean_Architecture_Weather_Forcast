package com.sungjae.weather_forcast.presentation.bindingadapter

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.sungjae.weather_forcast.presentation.base.BaseRecyclerViewAdapter
import com.sungjae.weather_forcast.presentation.component.RecyclerViewSpaceItemDecoration


/**
 *  @author use simplerecyclerview library
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(
    "contentItem",
    "contentItemLayout",
    "headerItem",
    "headerItemLayout",
    "footerItem",
    "footerItemLayout",
    "vm",
    "eventHolder",
    "hasfixedSize",
    requireAll = false
)
fun RecyclerView.setRecyclerViewAdapter(
    contentItem: List<Any>? = null,
    @LayoutRes contentItemRes: Int? = null,
    headerItem: Any? = null,
    @LayoutRes headerItemRes: Int? = null,
    footerItem: Any? = null,
    @LayoutRes footerItemRes: Int? = null,
    vm: Any? = null,
    eventHolder: Any? = null,
    hasFixedSize: Boolean = false,
) {
    val adapter = adapter as? BaseRecyclerViewAdapter ?: BaseRecyclerViewAdapter()
    adapter.contentLayoutResId = contentItemRes
    adapter.headerItem = headerItem
    adapter.headerLayoutResId = headerItemRes
    adapter.footerItem = footerItem
    adapter.footerLayoutResId = footerItemRes
    adapter.vm = vm
    adapter.eventHolder = eventHolder ?: vm

    this.adapter = adapter
    this.setHasFixedSize(hasFixedSize)

    contentItem?.let {
        adapter.updateData(it)
        adapter.notifyDataSetChanged()
    }
}

@BindingAdapter("itemSpace", "includeEdge", requireAll = false)
fun RecyclerView.setItemSpace(space: Float = 0f, includeEdge: Boolean = false) {
    val loop = itemDecorationCount
    for (i in 0 until loop) {
        val itemDecoration = getItemDecorationAt(i)
        if (itemDecoration is RecyclerViewSpaceItemDecoration) {
            removeItemDecorationAt(i)
            break
        }
    }
    addItemDecoration(RecyclerViewSpaceItemDecoration(space.toInt(), includeEdge))
}

@BindingAdapter("decorateItem")
fun RecyclerView.setItemDecoration(bool: Boolean) {
    if (bool) {

    }
    val dividerHorizontalItemDecoration =
        DividerItemDecoration(context, RecyclerView.HORIZONTAL)
    addItemDecoration(dividerHorizontalItemDecoration)
}
