package com.oleg.sokolov.gnbtrades.data.repository

import com.oleg.sokolov.gnbtrades.core.base.data.BaseRepository
import com.oleg.sokolov.gnbtrades.core.base.data.getData
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Success
import com.oleg.sokolov.gnbtrades.core.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import com.oleg.sokolov.gnbtrades.data.TransactionsApi
import com.oleg.sokolov.gnbtrades.data.database.dao.TransactionsDao
import com.oleg.sokolov.gnbtrades.data.database.model.TransactionEntityList
import com.oleg.sokolov.gnbtrades.domain.model.Transaction
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val transactionsApi: TransactionsApi,
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
                 transactionsApi.getTransactions().getData(
                     cacheAction = {
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
            TransactionEntityList(transactionsDao.getTransactions())
        }
    }

}