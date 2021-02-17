package com.salestaxes

class TaxableItemRepository {

    private val nonTaxableItems = listOf(
        "book",
        "chocolate bar",
        "box of chocolates",
        "headache pills"
    )

    fun isTaxable(itemName: String): Boolean {
        return !nonTaxableItems.contains(itemName.toLowerCase())
    }

}
