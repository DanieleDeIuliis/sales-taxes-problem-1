package com.salestaxes

import java.lang.StringBuilder

class ReceiptService(
    private val taxCalculator: TaxCalculator,
    private val costCalculator: CostCalculator
) {
    fun buildReceiptFor(basket: Basket): String {
        val stringBuilder = StringBuilder()
        basket.items.forEach { (item, quantity) -> stringBuilder.append("$quantity ${item.name}: ${costCalculator.computePriceAfterTaxOf(item)}\n")}
        stringBuilder
            .append("Sales taxes: ${taxCalculator.computeTotalTaxes(basket.items)}\n")
            .append("Total: ${costCalculator.computeTotalCost(basket.items)}")
        return stringBuilder.toString()
    }

}