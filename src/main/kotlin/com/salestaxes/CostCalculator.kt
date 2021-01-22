package com.salestaxes

class CostCalculator(private val taxCalculator: TaxCalculator) {

    fun computeTotalCost(items: Map<Item, Int>): Double {
        return items.map { (item, quantity) -> computePriceAfterTaxOf(item) * quantity }.sum()
    }

    fun computePriceAfterTaxOf(item: Item): Double {
        return taxCalculator.computeTaxAmount(item) + item.price
    }
}