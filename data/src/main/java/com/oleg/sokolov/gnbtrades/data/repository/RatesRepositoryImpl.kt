package com.oleg.sokolov.gnbtrades.data.repository


import com.oleg.sokolov.gnbtrades.data.GNBankApi
import com.oleg.sokolov.gnbtrades.data.common.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.data.common.utils.Connectivity
import com.oleg.sokolov.gnbtrades.data.database.dao.RatesDao
import com.oleg.sokolov.gnbtrades.data.database.model.RatesEntityList
import com.oleg.sokolov.gnbtrades.data.networking.base.getUpdatedDataFromCache
import com.oleg.sokolov.gnbtrades.domain.model.Rate
import com.oleg.sokolov.gnbtrades.domain.model.Result
import com.oleg.sokolov.gnbtrades.domain.repository.RatesRepository
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val GNBankApi: GNBankApi,
    private val ratesDao: RatesDao,
    connectivity: Connectivity,
    contextProvider: CoroutineContextProvider
) : BaseRepository<List<Rate>, RatesEntityList>(
    connectivity = connectivity,
    contextProvider = contextProvider
), RatesRepository {

    /*
    Get rates from Rest API if has connection, else get rates from DataBase
     */
    override suspend fun getRates(): Result<List<Rate>> {
        return fetchData(
            apiDataProvider = {
                GNBankApi.getRates().getUpdatedDataFromCache(
                    cacheAction = {
                        ratesDao.removeAll()
                        ratesDao.saveRate(it.rateList)
                    },
                    fetchFromCacheAction = {
                        RatesEntityList(ratesDao.getRates())
                    }
                )
            },
            dbDataProvider = {
                RatesEntityList(ratesDao.getRates())
            }
        )
    }
}

