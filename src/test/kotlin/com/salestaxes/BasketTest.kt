package com.salestaxes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasketTest {
    @Test
    fun `compute total tax amount`() {
        val items = mapOf(
            Item("chocolate bar", 10.0, false, false) to 1,
            Item("chair", 30.0, false, true) to 1,
            Item("cigars", 40.0, true, true) to 1
        )
        val taxCalculator = TaxCalculator()
        val basket = Basket(items, taxCalculator)
        val taxesAmount: Double = basket.computeTotalTaxes()
        assertThat(taxesAmount).isEqualTo(9.0)
    }

    @Test
    fun `compute cost after taxes of first item in the basket`() {
        val firstItem = Item("cigars", 40.0, true, true)
        val items = mapOf(
            firstItem to 1,
            Item("chocolate bar", 10.0, false, false) to 1,
            Item("chair", 30.0, false, true) to 1
        )
        val taxCalculator = TaxCalculator()
        val basket = Basket(items, taxCalculator)
        val priceAfterTaxes: Double = basket.computePriceAfterTaxOf(firstItem)
        assertThat(priceAfterTaxes).isEqualTo(46.0)
    }

    @Test
    fun `compute total cost of items in the basket`() {
        val firstItem = Item("cigars", 40.0, true, true)
        val items = mapOf(
            firstItem to 1,
            Item("chocolate bar", 10.0, false, false) to 1,
            Item("chair", 30.0, false, true) to 1
        )
        val taxCalculator = TaxCalculator()
        val basket = Basket(items, taxCalculator)
        val totalPrice: Double = basket.computeTotalCost()
        assertThat(totalPrice).isEqualTo(89.0)
    }

    @Test
    fun `compute total cost of items in the basket with items with multiple quantity`() {
        val firstItem = Item("cigars", 40.0, true, true)
        val items = mapOf(
            firstItem to 1,
            Item("chocolate bar", 10.0, false, false) to 2,
            Item("chair", 30.0, false, true) to 1
        )
        val taxCalculator = TaxCalculator()
        val basket = Basket(items, taxCalculator)
        val totalPrice: Double = basket.computeTotalCost()
        assertThat(totalPrice).isEqualTo(99.0)
    }
}

