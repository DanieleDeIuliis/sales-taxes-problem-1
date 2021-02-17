package com.salestaxes

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TaxCalculatorTest {

    private val taxableItemRepository: TaxableItemRepository = mockk(relaxed = true)

    @Test
    fun `call isTaxable when computing tax amount`() {
        val taxCalculator = TaxCalculator(taxableItemRepository = taxableItemRepository)

        taxCalculator.computeTaxFor(Item("book", false, 10.00, 1))

        verify { taxableItemRepository.isTaxable("book") }
    }

    @Test
    fun `returns the base taxable amount for a non imported item`() {

        every { taxableItemRepository.isTaxable("book") } returns true
        val taxCalculator = TaxCalculator(taxableItemRepository = taxableItemRepository)

        val taxAmount = taxCalculator.computeTaxFor(Item("book", false, 10.00, 1))

        assertThat(taxAmount).isEqualTo(1.00)
    }

    @Test
    fun `returns the base taxable amount for an imported item`() {

        every { taxableItemRepository.isTaxable("book") } returns true
        val taxCalculator = TaxCalculator(taxableItemRepository = taxableItemRepository)

        val taxAmount = taxCalculator.computeTaxFor(Item("book", true, 10.00, 1))

        assertThat(taxAmount).isEqualTo(1.50)
    }

    @Test
    fun `returns the base taxable amount for a non imported item rounded at 0,05`() {

        every { taxableItemRepository.isTaxable("book") } returns true
        val taxCalculator = TaxCalculator(taxableItemRepository = taxableItemRepository)

        val taxAmount = taxCalculator.computeTaxFor(Item("book", false, 14.99, 1))

        assertThat(taxAmount).isEqualTo(1.50)
    }
}