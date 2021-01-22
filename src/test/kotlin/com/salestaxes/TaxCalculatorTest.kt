package com.salestaxes

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TaxCalculatorTest {

    private val taxableRepository: TaxableInMemoryRepository = mockk()

    @Test
    fun `compute tax amount of imported item`() {
        every { taxableRepository.isTaxable("tv magazine") } returns false
        val item = Item("tv magazine", 10.0, true)
        val taxCaculator = TaxCalculator(taxableRepository)
        val percentageAmount = taxCaculator.computeTaxAmount(item)

        assertThat(percentageAmount).isEqualTo(0.5)
    }

    @Test
    fun `compute tax amount of taxable item`() {
        every { taxableRepository.isTaxable("tv magazine") } returns true
        val item = Item("tv magazine", 10.0, false)
        val taxCaculator = TaxCalculator(taxableRepository)
        val percentageAmount = taxCaculator.computeTaxAmount(item)

        assertThat(percentageAmount).isEqualTo(1.0)
    }

    @Test
    fun `compute tax amount of taxable imported item`() {
        every { taxableRepository.isTaxable("tv magazine") } returns true
        val item = Item("tv magazine", 10.0, true)
        val taxCaculator = TaxCalculator(taxableRepository)
        val percentageAmount = taxCaculator.computeTaxAmount(item)

        assertThat(percentageAmount).isEqualTo(1.5)
    }

    @Test
    fun `compute total tax amount`() {
        every { taxableRepository.isTaxable(any()) } returns true
        val items = mapOf(
            Item("chocolate bar", 10.0, false) to 1,
            Item("chair", 30.0, true) to 1,
            Item("cigars Imported", 40.0, true) to 1
        )
        val taxesAmount: Double = TaxCalculator(taxableRepository).computeTotalTaxes(items)
        assertThat(taxesAmount).isEqualTo(11.5)
    }
}

