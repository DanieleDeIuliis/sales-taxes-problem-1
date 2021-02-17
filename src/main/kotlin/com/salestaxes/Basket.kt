package com.salestaxes

data class Basket(val items: List<BasketItem>, val totalPrice: Double, val totalTaxes: Double) {
}

data class BasketItem(val name: String, val isImported: Boolean, val quantity: Int, val grossPrice: Double, val totalTaxAmount: Double)