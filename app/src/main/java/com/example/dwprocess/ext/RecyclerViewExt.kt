package com.example.dwprocess.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dwprocess.base.BaseRecyclerViewAdapter


@BindingAdapter(value = ["replaceAll"])
fun RecyclerView.replaceAll(list: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (this.adapter as? BaseRecyclerViewAdapter<Any, *>)?.let {
        it.replaceAll(list)
        it.notifyDataSetChanged()
    }
}
