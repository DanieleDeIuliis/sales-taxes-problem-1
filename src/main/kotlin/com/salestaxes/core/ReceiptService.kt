package com.salestaxes.core

import java.lang.String.format
import java.lang.StringBuilder

class ReceiptService(
    private val taxCalculator: TaxCalculator,
    private val costCalculator: CostCalculator
) {
    fun buildReceiptFor(basket: Map<Item, Int>): String {
        val stringBuilder = StringBuilder()
        basket.forEach { (item, quantity) -> stringBuilder
            .append("$quantity ${item.name}: ${costCalculator.computePriceAfterTaxOf(item, quantity)}\n")}
        stringBuilder
            .append(format("Sales Taxes: %.2f\n",taxCalculator.computeTotalTaxes(basket)))
            .append(format("Total: %.2f", costCalculator.computeTotalCost(basket)))
        return stringBuilder.toString()
    }

}