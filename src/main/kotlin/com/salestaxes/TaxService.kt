package com.salestaxes

class TaxService(private val taxCalculator: TaxCalculator) {
    fun computeTax(items: List<Item>): Basket {
        val basketItems = items.map { item ->
            val tax = taxCalculator.computeTaxFor(item)
            BasketItem(item.name, item.isImported, item.quantity,(item.price + tax) * item.quantity, tax * item.quantity)
        }
        return Basket(basketItems)
    }

}