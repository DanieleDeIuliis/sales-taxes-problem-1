package com.salestaxes

class TaxService(private val taxCalculator: TaxCalculator) {
    fun computeTax(items: List<Item>): Basket {
        val basketItems = items.map { item ->
            val tax = taxCalculator.computeTaxFor(item)
            BasketItem(item.name, item.isImported, item.quantity,(item.price + tax) * item.quantity, tax * item.quantity)
        }
        return Basket(basketItems, computeTotalCost(basketItems), computeTotalTaxes(basketItems))
    }

    private fun computeTotalTaxes(basketItems: List<BasketItem>) =
        basketItems.map(BasketItem::totalTaxAmount).sum()

    private fun computeTotalCost(basketItems: List<BasketItem>) =
        basketItems.map(BasketItem::grossPrice).sum()

}