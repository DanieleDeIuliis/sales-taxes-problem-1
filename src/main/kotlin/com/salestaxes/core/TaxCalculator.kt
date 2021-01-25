package com.salestaxes.core

import java.math.RoundingMode
import kotlin.math.roundToInt


class TaxCalculator(private val taxableRepository: TaxableRepository) {
    fun computeTaxAmount(item: Item): Double {
        var taxPercentage = 0
        if(item.isImported) {
            taxPercentage += 5
        }
        if(taxableRepository.isTaxable(item.name)) {
            taxPercentage += 10
        }
        // poco leggibile
        return (taxPercentage.div(100.0) * item.price * 20.0).roundToInt() / 20.0
    }

    fun computeTotalTaxes(items: Map<Item, Int>): Double {
        return items.map { (item, quantity) ->  computeTaxAmount(item) * quantity }.sum()
    }

}