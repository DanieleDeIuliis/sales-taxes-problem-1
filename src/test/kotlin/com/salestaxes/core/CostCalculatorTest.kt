package com.salestaxes.core

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CostCalculatorTest {
    private val taxCalculator: TaxCalculator = mockk()

    @Test
    fun `compute cost after taxes of first item in the basket`() {
        every { taxCalculator.computeTaxAmount(any()) } returns 1.50

        val firstItem = Item("cigars", 14.99, false)
        val costCalculator = CostCalculator(taxCalculator)
        val priceAfterTaxes = costCalculator.computePriceAfterTaxOf(firstItem, 1)
        Assertions.assertThat(priceAfterTaxes).isEqualTo(16.49.toBigDecimal())
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
        val totalPrice= costCalculator.computeTotalCost(items)
        Assertions.assertThat(totalPrice).isEqualTo(89.toBigDecimal().setScale(2))
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
        val totalPrice= costCalculator.computeTotalCost(items)
        Assertions.assertThat(totalPrice).isEqualTo(102.toBigDecimal().setScale(2))
    }
}