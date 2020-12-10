package com.oleg.sokolov.gnbtrades.data.repository

import com.oleg.sokolov.gnbtrades.core.base.data.BaseRepository
import com.oleg.sokolov.gnbtrades.core.base.data.getUpdatedDataFromCache
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Failure
import com.oleg.sokolov.gnbtrades.core.base.domain.model.HttpError
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Success
import com.oleg.sokolov.gnbtrades.core.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import com.oleg.sokolov.gnbtrades.data.GNBankApi
import com.oleg.sokolov.gnbtrades.data.database.dao.TransactionsDao
import com.oleg.sokolov.gnbtrades.data.database.model.TransactionEntityList
import com.oleg.sokolov.gnbtrades.data.networking.DB_ENTRY_ERROR
import com.oleg.sokolov.gnbtrades.domain.model.Transaction
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val GNBankApi: GNBankApi,
    private val transactionsDao: TransactionsDao,
    connectivity: Connectivity,
    contextProvider: CoroutineContextProvider
) : BaseRepository<List<Transaction>, TransactionEntityList>(
    connectivity = connectivity,
    contextProvider = contextProvider
), TransactionsRepository {

    override suspend fun getTransactions(): Result<List<Transaction>> {
        return fetchData(
            apiDataProvider = {
                GNBankApi.getTransactions().getUpdatedDataFromCache(
                    cacheAction = { it ->
                        transactionsDao.saveTransaction(it.transactionList)
                    },
                    fetchFromCacheAction = {
                        TransactionEntityList(transactionsDao.getTransactions())
                    }
                )
            },
            dbDataProvider = {
                TransactionEntityList(transactionsDao.getTransactions())
            }
        )
    }

    override suspend fun getTransactions(product: String): Result<List<Transaction>> {
        return fetchData {
            TransactionEntityList(transactionsDao.getTransactions(product))
        }
    }

    override suspend fun getProductsNames(): Result<List<String>> {
        return withContext(contextProvider.io) {
            val dbResult = transactionsDao.getProductsNames()
            if (dbResult != null) Success(dbResult) else Failure(
                HttpError(
                    Throwable(DB_ENTRY_ERROR)
                )
            )
        }
    }

}