package com.salestaxes.core

import java.math.BigDecimal
import java.math.RoundingMode

class CostCalculator(private val taxCalculator: TaxCalculator) {

    fun computeTotalCost(items: Map<Item, Int>): BigDecimal {
        return items.map { (item, quantity) -> computePriceAfterTaxOf(item, quantity) }.reduce(BigDecimal::add)
    }

    fun computePriceAfterTaxOf(item: Item, quantity: Int): BigDecimal {
        return (taxCalculator.computeTaxAmount(item) + item.price).times(quantity)
            .toBigDecimal().setScale(2, RoundingMode.HALF_UP)
    }
}