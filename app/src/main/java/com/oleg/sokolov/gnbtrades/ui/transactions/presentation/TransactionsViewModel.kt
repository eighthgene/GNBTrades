package com.oleg.sokolov.gnbtrades.ui.transactions.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.oleg.sokolov.gnbtrades.core.base.domain.model.onFailure
import com.oleg.sokolov.gnbtrades.core.base.domain.model.onSuccess
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.Error
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.Success
import com.oleg.sokolov.gnbtrades.core.base.presentation.viewmodel.BaseViewModel
import com.oleg.sokolov.gnbtrades.core.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.core.extensions.roundUI
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.GetRatesUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.GetTransactionsUseCase
import com.oleg.sokolov.gnbtrades.domain.model.ExchangeListTo
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsAction
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsScreeData
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsViewEffects
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsScreen


class TransactionsViewModel @ViewModelInject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getRatesUseCase: GetRatesUseCase,
    conn: Connectivity,
    contextProvider: CoroutineContextProvider
) : BaseViewModel<TransactionsScreeData, TransactionsAction, TransactionsViewEffects>(
    conn,
    contextProvider
) {

    private val transactions = ArrayList<TransactionsScreen>()
    private var total: String = "0"
    private val defaultCurrency = "EUR"

    override fun onAction(action: TransactionsAction) {
        when (action) {
            is TransactionsAction.OnViewLoaded -> loadProductTransactions(action.product)
        }
    }

    private fun loadProductTransactions(product: String) {
        executeUseCase {
            getTransactionsUseCase(product)
                .onSuccess { transactionList ->

                    getRatesUseCase(ExchangeListTo(transactionList, defaultCurrency))
                        .onSuccess { exchangeListFrom ->
                            val mappedList = exchangeListFrom.list.map {
                                TransactionsScreen(
                                    it.name,
                                    it.currency,
                                    it.amount.roundUI(),
                                    it.amountConverted.roundUI()
                                )
                            }

                            transactions.clear()
                            transactions.addAll(mappedList)
                            total = exchangeListFrom.amount.roundUI()

                            runOnUI {
                                _viewState.value = Success(TransactionsScreeData(mappedList, exchangeListFrom.amount.roundUI()))
                            }
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