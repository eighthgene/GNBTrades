package com.oleg.sokolov.gnbtrades.ui.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import com.oleg.sokolov.gnbtrades.common.EMPTY_STRING
import com.oleg.sokolov.gnbtrades.common.extensions.gone
import com.oleg.sokolov.gnbtrades.common.extensions.snackbar
import com.oleg.sokolov.gnbtrades.common.extensions.visible

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewReady()
    }

    abstract fun viewReady()

    abstract fun getLayout(): Int

    open fun showError(@StringRes errorMessage: Int, rootView: View) =
        snackbar(errorMessage, rootView)

    open fun showError(errorMessage: String?, rootView: View) =
        snackbar(errorMessage ?: EMPTY_STRING, rootView)

    open fun showLoading(progressBar: ProgressBar) = progressBar.visible()

    open fun hideLoading(progressBar: ProgressBar) = progressBar.gone()

    open fun hide(group: Group) = group.gone()

    open fun show(group: Group) = group.visible()
}