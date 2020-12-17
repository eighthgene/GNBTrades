package com.oleg.sokolov.gnbtrades.domain.interaction.rates.impl

import com.oleg.sokolov.gnbtrades.domain.exceptions.RateNotFoundException
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.GetRatesUseCase
import com.oleg.sokolov.gnbtrades.domain.model.*
import com.oleg.sokolov.gnbtrades.domain.repository.RatesRepository
import timber.log.Timber
import java.math.BigDecimal
import javax.inject.Inject

class GetRatesUseCaseImpl @Inject constructor(
    private val ratesRepository: RatesRepository
) : GetRatesUseCase {

    private val rates = ArrayList<Rate>()
    private var visited: HashSet<String> = HashSet()
    private lateinit var actualRate: BigDecimal

    override suspend fun invoke(param: ExchangeListTo): Result<ExchangeListFrom> {
        if (rates.isEmpty()) {
            ratesRepository.getRates()
                .onSuccess {
                    rates.addAll(it)
                }
                .onFailure {
                    Failure(BusinessError(RateNotFoundException()))
                }
        }
        var sum: BigDecimal = BigDecimal.ZERO
        val transactionExchangeList = mutableListOf<TransactionsExchanged>()
        for (transaction in param.list) {
            val rate: BigDecimal = if (transaction.currency == param.to) {
                BigDecimal.ONE
            } else {
                getPairRate(transaction.currency, param.to)
            }
            Timber.d("Rate for ${transaction.currency} to EUR is: $rate")
            Timber.d("All rates are: $rates")
            val exchangedAmount: BigDecimal = transaction.amount.multiply(rate)
            transactionExchangeList.add(
                TransactionsExchanged(
                    transaction.name,
                    transaction.amount,
                    transaction.currency,
                    exchangedAmount
                )
            )
            sum = sum.add(exchangedAmount)
        }

        return if (sum.compareTo(BigDecimal.ZERO) != 0) {
            Success(ExchangeListFrom(transactionExchangeList, sum))
        } else {
            Failure(BusinessError(RateNotFoundException()))
        }
    }

    private fun getPairRate(from: String, to: String): BigDecimal {
        visited.clear()
        actualRate = BigDecimal.ONE
        actualRate = getRate(from, to, BigDecimal.ONE)
        return actualRate
    }

    private fun getRate(from: String, to: String, rate: BigDecimal): BigDecimal {
        val existingRate = getExistingRate(from, to)
        if (existingRate.compareTo(BigDecimal.ZERO) != 0) {
            return rate.multiply(existingRate)
        } else {
            visited.add(from)
            for (_rate in rates) {
                if (_rate.from == from && !visited.contains(_rate.to)) {
                    actualRate = getRate(_rate.to, to, rate.multiply(_rate.rate))
                    if (actualRate != BigDecimal.ZERO) {
                        return actualRate
                    }
                }
            }
        }
        return BigDecimal.ZERO
    }

    private fun getExistingRate(from: String, to: String): BigDecimal {
        for (rate in rates) {
            if (rate.from == from && rate.to == to) return rate.rate
        }
        return BigDecimal.ZERO
    }

}