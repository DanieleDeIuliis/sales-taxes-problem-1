package com.salestaxes

class Basket(private val items: Map<Item, Int>, private val taxCalculator: TaxCalculator) {
    fun computeTotalTaxes(): Double {
        return items.map { (item, quantity) ->  taxCalculator.computeTaxAmount(item) * quantity }.sum()
    }

    fun computePriceAfterTaxOf(item: Item): Double {
        return taxCalculator.computeTaxAmount(item) + item.price
    }

    fun computeTotalCost(): Double {
        return items.map { (item, quantity) -> computePriceAfterTaxOf(item) * quantity }.sum()
    }

}