package com.oleg.sokolov.gnbtrades.core.base.presentation.adapter

import android.view.View

interface BaseAdapterCallback<T> {
    fun onItemClick(position: Int, view: View)
    fun onLongClick(position: Int, view: View): Boolean
}