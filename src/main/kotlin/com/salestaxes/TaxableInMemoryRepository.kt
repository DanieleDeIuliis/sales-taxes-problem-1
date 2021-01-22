package com.salestaxes

class TaxableInMemoryRepository: TaxableRepository {
    private val taxableItems = listOf(
        "chocolate bar",
        "chair",
        "cigars",
        "tv magazine"
    )
    override fun isTaxable(itemName: String): Boolean {
        return taxableItems.contains(itemName)
    }

}