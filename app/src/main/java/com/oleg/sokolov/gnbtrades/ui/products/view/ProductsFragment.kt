package com.oleg.sokolov.gnbtrades.ui.products.view

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleg.sokolov.gnbtrades.R
import com.oleg.sokolov.gnbtrades.core.base.presentation.adapter.BaseAdapterCallback
import com.oleg.sokolov.gnbtrades.core.base.presentation.view.*
import com.oleg.sokolov.gnbtrades.core.extensions.gone
import com.oleg.sokolov.gnbtrades.core.extensions.snackbar
import com.oleg.sokolov.gnbtrades.core.extensions.subscribe
import com.oleg.sokolov.gnbtrades.core.extensions.visible
import com.oleg.sokolov.gnbtrades.domain.exceptions.EmptyListException
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsAction
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsScreen
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsViewEffects
import com.oleg.sokolov.gnbtrades.ui.products.presentation.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : BaseFragment() {

    private val viewModel: ProductsViewModel by viewModels()

    @Inject
    lateinit var adapter: ProductsAdapter

    override fun getLayout():  Int = R.layout.fragment_products

    override fun viewReady() {
        viewModel.onAction(ProductsAction.OnViewStarted)
        subscribeToData()
        setupRecyclerView()
    }

    private fun subscribeToData() {
        viewModel.viewState.subscribe(this, ::renderState)
        viewModel.viewEffects.subscribe(this, ::handleViewEffects)
    }

    private fun renderState(viewState: ViewState<List<ProductsScreen>>) {
        when (viewState) {
            is Success -> showProductsList(viewState.data)
            is Error -> handleError(viewState.error)
            is Loading -> show(products_loader)
            is NoInternetState -> showNoInternetError()
        }
    }

    private fun handleViewEffects(viewEffect: ProductsViewEffects) {
        when (viewEffect) {
            is ProductsViewEffects.NavigateToDetails ->    {
                val action = ProductsFragmentDirections.actionProductsFragmentToDetailsFragment(viewEffect.product)
                findNavController().navigate(action)
            }
        }
    }

    private fun showProductsList(products: List<ProductsScreen>) {
        hide(products_loader)
        updateLoading(products)
        adapter.updateItems(products)
    }

    private fun updateLoading(listData: List<ProductsScreen>) {
        if (listData.isNotEmpty()) {
            productsRecycler.visible()
            emptyList.gone()
        } else {
            productsRecycler.gone()
            emptyList.visible()
        }
    }

    private fun handleError(error: Throwable) {
        hide(products_loader)
        val msg: String = when (error) {
            is EmptyListException -> getString(R.string.empty_list_exception)
            else -> getString(R.string.error_default)
        }
        showError(msg, productsContainer)
    }

    private fun showNoInternetError() {
        hide(products_loader)
        snackbar(getString(R.string.no_internet_error_message), productsContainer)
    }

    /**
     * SETUP and BIND Section
     */
    private fun setupRecyclerView() {
        productsRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter.attachCallback(object : BaseAdapterCallback<ProductsScreen> {
            override fun onItemClick(position: Int, view: View) {
                viewModel.onAction(ProductsAction.OnItemClick(position))
            }

            override fun onLongClick(position: Int, view: View): Boolean {
                return false
            }
        })
        productsRecycler.adapter = adapter
        setupDivider()
    }

    private fun setupDivider(){
        context?.let { context ->
            ContextCompat.getDrawable(context, R.drawable.divider)?.let {
                val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
                dividerItemDecoration.setDrawable(it)
                productsRecycler.addItemDecoration(dividerItemDecoration)
            }
        }
    }



}