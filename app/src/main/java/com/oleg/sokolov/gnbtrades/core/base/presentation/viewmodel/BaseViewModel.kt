package com.oleg.sokolov.gnbtrades.core.base.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.Loading
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.ViewState
import com.oleg.sokolov.gnbtrades.core.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.core.extensions.async
import com.oleg.sokolov.gnbtrades.core.extensions.launch
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import kotlinx.coroutines.withContext


abstract class BaseViewModel<T : Any, A, E> constructor(
    var connectivity: Connectivity,
    var contextProvider: CoroutineContextProvider
) : ViewModel() {

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

    protected suspend fun executeUseCase(action: suspend () -> Unit, action2: suspend () -> Unit) {
        _viewState.value = Loading()
        val a1 = async(contextProvider.io) { action() }
        a1.await()
        val a2 = async(contextProvider.io) { action2() }
        a2.await()
    }

    protected suspend fun executeSyncUseCase(action: suspend () -> Unit) {
        async(contextProvider.io) { action() }.await()
    }

    protected suspend fun runOnUI(action: suspend () -> Unit) {
        withContext(contextProvider.main) {
            action()
        }
    }


}