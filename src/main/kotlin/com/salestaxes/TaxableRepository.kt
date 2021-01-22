package com.salestaxes

interface TaxableRepository {
    fun isTaxable(itemName: String): Boolean
}