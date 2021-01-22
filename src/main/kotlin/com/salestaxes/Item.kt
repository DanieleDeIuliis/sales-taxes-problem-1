package com.salestaxes

data class Item(val name: String, val price: Double, val isTaxable: Boolean) {
    fun isImported(): Boolean {
        return name.contains("imported", ignoreCase = true)
    }
}