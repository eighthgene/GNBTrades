package com.oleg.sokolov.gnbtrades.ui.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleg.sokolov.gnbtrades.common.extensions.launch
import com.oleg.sokolov.gnbtrades.data.common.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.data.common.utils.Connectivity
import com.oleg.sokolov.gnbtrades.ui.base.view.Loading
import com.oleg.sokolov.gnbtrades.ui.base.view.ViewState
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject


abstract class BaseViewModel<T : Any, A, E> : ViewModel(), KoinComponent {

    protected val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState

    protected val _viewEffects = SingleLiveEvent<E>()
    val viewEffects: LiveData<E>
        get() = _viewEffects

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        _viewState.value = Loading()
        if (connectivity.hasNetworkAccess()) {
            launch { action() }
        } else {
            noInternetAction()
        }
    }

    abstract fun onAction(action: A)

    protected fun executeUseCase(action: suspend () -> Unit) {
        _viewState.value = Loading()
        launch(contextProvider.io) { action() }
    }

    protected suspend fun runOnUI(action: suspend () -> Unit) {
        withContext(contextProvider.main) {
            action()
        }
    }


}