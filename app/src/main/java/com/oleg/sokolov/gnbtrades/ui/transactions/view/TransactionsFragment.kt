package com.oleg.sokolov.gnbtrades.ui.transactions.view

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleg.sokolov.gnbtrades.App
import com.oleg.sokolov.gnbtrades.R
import com.oleg.sokolov.gnbtrades.common.extensions.gone
import com.oleg.sokolov.gnbtrades.common.extensions.subscribe
import com.oleg.sokolov.gnbtrades.common.extensions.visible
import com.oleg.sokolov.gnbtrades.domain.exceptions.EmptyListException
import com.oleg.sokolov.gnbtrades.ui.base.view.*
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsAction
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsScreeData
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsScreen
import com.oleg.sokolov.gnbtrades.ui.transactions.presentation.TransactionsViewModel
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject

class TransactionsFragment : BaseFragment() {

    private val args: TransactionsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TransactionsViewModel>{ viewModelFactory }

    @Inject
    lateinit var adapter: TransactionsAdapter

    override fun viewReady() {
        viewModel.onAction(TransactionsAction.OnViewLoaded(args.productNameArg))
        subscribeToData()
        setupRecyclerView()
    }

    override fun initDagger() {
        (activity?.application as App).component.inject(this)
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

    private fun renderState(viewState: ViewState<TransactionsScreeData>) {
        when (viewState) {
            is Success -> {
                showListData(viewState.data.list)
                updateTotal(viewState.data.total)
            }
            is Error -> handleError(viewState.error)
            is Loading -> showLoading(listLoadingProgress)
            is NoInternetState -> showNoInternetError()
        }
    }

    private fun updateTotal(total: String) {
        transaction_total.text = total
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