package com.oleg.sokolov.gnbtrades.ui.transactions.view

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleg.sokolov.gnbtrades.R
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.*
import com.oleg.sokolov.gnbtrades.core.extensions.gone
import com.oleg.sokolov.gnbtrades.core.extensions.subscribe
import com.oleg.sokolov.gnbtrades.core.extensions.visible
import com.oleg.sokolov.gnbtrades.domain.exceptions.EmptyListException
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsScreen
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsAction
import com.oleg.sokolov.gnbtrades.ui.transactions.presentation.TransactionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject

@AndroidEntryPoint
class TransactionsFragment : BaseFragment() {

    private val args: TransactionsFragmentArgs by navArgs()
    private val viewModel: TransactionsViewModel by viewModels()

    @Inject
    lateinit var adapter: TransactionsAdapter

    override fun viewReady() {
        viewModel.onAction(TransactionsAction.OnViewLoaded(args.productNameArg))
        subscribeToData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        transactionsRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        transactionsRecycler.adapter = adapter

        context?.let { context ->
            ContextCompat.getDrawable(context, R.drawable.divider)?.let {
                val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
                dividerItemDecoration.setDrawable(it)
                transactionsRecycler.addItemDecoration(dividerItemDecoration)
            }
        }

    }

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::renderState)
    }

    private fun renderState(viewState: ViewState<List<TransactionsScreen>>) {
        when (viewState) {
            is Success -> showListData(viewState.data)
            is Error -> handleError(viewState.error)
            is Loading -> showLoading(listLoadingProgress)
            is NoInternetState -> showNoInternetError()
        }
    }

    private fun showNoInternetError() {}

    private fun showListData(data: List<TransactionsScreen>) {
        hideLoading(listLoadingProgress)
        isEmptyList(data)
        adapter.updateItems(data)
    }

    private fun isEmptyList(listData: List<TransactionsScreen>) {
        if (listData.isNotEmpty()) {
            transactionsRecycler.visible()
            listEmpty.gone()
        } else {
            transactionsRecycler.gone()
            listEmpty.visible()
        }
    }

    private fun handleError(error: Throwable) {
        hideLoading(listLoadingProgress)
        val msg: String = when (error) {
            is EmptyListException -> getString(R.string.empty_list_exception)
            else -> getString(R.string.error_default)
        }
        showError(msg, transactions_container)
    }


    override fun getLayout(): Int = R.layout.fragment_transactions


}