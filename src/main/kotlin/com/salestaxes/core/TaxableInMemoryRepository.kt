package com.salestaxes.core

class TaxableInMemoryRepository: TaxableRepository {
    private val nonTaxableItems = listOf(
        "chocolate bar",
        "book",
        "cigars",
        "tv magazine"
    )
    override fun isTaxable(itemName: String): Boolean {
        return !nonTaxableItems.contains(itemName)
    }

}