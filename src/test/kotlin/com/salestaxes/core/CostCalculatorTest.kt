package com.salestaxes.core

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CostCalculatorTest {
    private val taxCalculator: TaxCalculator = mockk()

    @Test
    fun `compute cost after taxes of first item in the basket`() {
        every { taxCalculator.computeTaxAmount(any()) } returns 6.0

        val firstItem = Item("cigars", 40.0, true)
        val costCalculator = CostCalculator(taxCalculator)
        val priceAfterTaxes: Double = costCalculator.computePriceAfterTaxOf(firstItem)
        Assertions.assertThat(priceAfterTaxes).isEqualTo(46.0)
    }

    @Test
    fun `compute total cost of items in the basket`() {
        every { taxCalculator.computeTaxAmount(any()) } returns 3.0

        val firstItem = Item("cigars", 40.0, true)
        val items = mapOf(
            firstItem to 1,
            Item("chocolate bar", 10.0, false) to 1,
            Item("chair", 30.0,  true) to 1
        )
        val costCalculator = CostCalculator(taxCalculator)
        val totalPrice: Double = costCalculator.computeTotalCost(items)
        Assertions.assertThat(totalPrice).isEqualTo(89.0)
    }

    @Test
    fun `compute total cost of items in the basket with items with multiple quantity`() {
        every { taxCalculator.computeTaxAmount(any()) } returns 3.0
        val firstItem = Item("cigars", 40.0, true)
        val items = mapOf(
            firstItem to 1,
            Item("chocolate bar", 10.0, false) to 2,
            Item("chair", 30.0, true) to 1
        )
        val taxCalculator = taxCalculator
        val costCalculator = CostCalculator(taxCalculator)
        val totalPrice: Double = costCalculator.computeTotalCost(items)
        Assertions.assertThat(totalPrice).isEqualTo(102.0)
    }
}