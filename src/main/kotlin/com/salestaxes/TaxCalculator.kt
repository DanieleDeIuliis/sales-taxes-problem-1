package com.salestaxes

import java.math.BigDecimal
import java.math.RoundingMode

class TaxCalculator(private val taxableItemRepository: TaxableItemRepository) {
    fun computeTaxFor(item: Item): Double {
        var taxablePercentage = 0
        if(taxableItemRepository.isTaxable(item.name)) {
            taxablePercentage += 10
        }
        if(item.isImported) {
          taxablePercentage += 5
        }
        return item.price.applyPercentageAndScale(taxablePercentage)

    }
}

fun Double.applyPercentageAndScale(percentage: Int): Double {
    return BigDecimal(this).times(percentage.toBigDecimal()).divide(BigDecimal(100.00)).setScale(2, RoundingMode.UP).toDouble()
}