package com.salestaxes

data class Basket(val items: List<BasketItem>) {
}

data class BasketItem(val name: String, val isImported: Boolean, val quantity: Int, val grossPrice: Double, val totalTaxAmount: Double)