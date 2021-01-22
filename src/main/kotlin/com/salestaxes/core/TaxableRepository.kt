package com.salestaxes.core

interface TaxableRepository {
    fun isTaxable(itemName: String): Boolean
}