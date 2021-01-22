package com.salestaxes

import java.lang.StringBuilder

class ReceiptService(
    private val taxCalculator: TaxCalculator,
    private val costCalculator: CostCalculator
) {
    fun buildReceiptFor(basket: Map<Item, Int>): String {
        val stringBuilder = StringBuilder()
        basket.forEach { (item, quantity) -> stringBuilder.append("$quantity ${item.name}: ${costCalculator.computePriceAfterTaxOf(item)}\n")}
        stringBuilder
            .append("Sales taxes: ${taxCalculator.computeTotalTaxes(basket)}\n")
            .append("Total: ${costCalculator.computeTotalCost(basket)}")
        return stringBuilder.toString()
    }

}