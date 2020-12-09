package com.oleg.sokolov.gnbtrades.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.oleg.sokolov.gnbtrades.R
import com.oleg.sokolov.gnbtrades.data.TransactionsApi
import com.oleg.sokolov.gnbtrades.data.networking.ResponseAdapter
import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsAction
import com.oleg.sokolov.gnbtrades.ui.products.presentation.ProductsViewModel
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.onAction(ProductsAction.OnStart)
        
    }


}