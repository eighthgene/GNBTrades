package com.oleg.sokolov.gnbtrades.ui.transactions.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.oleg.sokolov.gnbtrades.core.base.domain.model.onFailure
import com.oleg.sokolov.gnbtrades.core.base.domain.model.onSuccess
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.Error
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.Success
import com.oleg.sokolov.gnbtrades.core.base.presentation.viewmodel.BaseViewModel
import com.oleg.sokolov.gnbtrades.core.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.GetTransactionsUseCase
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsAction
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsViewEffects
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsScreen
import timber.log.Timber

class TransactionsViewModel @ViewModelInject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    conn: Connectivity,
    contextProvider: CoroutineContextProvider
) : BaseViewModel<List<TransactionsScreen>, TransactionsAction, TransactionsViewEffects>(
    conn,
    contextProvider
) {

    private val transactions = ArrayList<TransactionsScreen>()

    override fun onAction(action: TransactionsAction) {
        when (action) {
            is TransactionsAction.OnViewLoaded -> loadProductTransactions(action.product)
        }
    }

    private fun loadProductTransactions(product: String) {
        executeUseCase {
            getTransactionsUseCase(product)
                .onSuccess { transaction ->
                    Timber.d("Result is: $transaction")
                    val mappedData = transaction.map {
                        TransactionsScreen(
                            it.name,
                            it.currency,
                            it.amount.toString(),
                            "0"
                        )
                    }
                    transactions.clear()
                    transactions.addAll(mappedData)
                    runOnUI {
                        _viewState.value = Success(transactions)
                    }
                }
                .onFailure {
                    runOnUI {
                        _viewState.value = Error(it.throwable)
                    }
                }
        }
    }
}