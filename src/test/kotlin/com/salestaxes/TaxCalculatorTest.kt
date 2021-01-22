package com.salestaxes

import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TaxCalculatorTest {

    @Test
    fun `compute tax amount of imported item`() {
        val item = Item("tv magazine", 10.0, true, false)
        val taxCaculator = TaxCalculator()
        val percentageAmount = taxCaculator.computeTaxAmount(item)

        assertThat(percentageAmount).isEqualTo(0.5)
    }

    @Test
    fun `compute tax amount of taxable item`() {
        val item = Item("tv magazine", 10.0, false, true)
        val taxCaculator = TaxCalculator()
        val percentageAmount = taxCaculator.computeTaxAmount(item)

        assertThat(percentageAmount).isEqualTo(1.0)
    }

    @Test
    fun `compute tax amount of taxable imported item`() {
        val item = Item("tv magazine", 10.0, true, true)
        val taxCaculator = TaxCalculator()
        val percentageAmount = taxCaculator.computeTaxAmount(item)

        assertThat(percentageAmount).isEqualTo(1.5)
    }

    @Test
    fun `compute total tax amount`() {
        val taxCaculator = TaxCalculator()
        val items = mapOf(
            Item("chocolate bar", 10.0, false, false) to 1,
            Item("chair", 30.0, false, true) to 1,
            Item("cigars", 40.0, true, true) to 1
        )
        val taxesAmount: Double = TaxCalculator().computeTotalTaxes(items)
        assertThat(taxesAmount).isEqualTo(9.0)
    }
}

