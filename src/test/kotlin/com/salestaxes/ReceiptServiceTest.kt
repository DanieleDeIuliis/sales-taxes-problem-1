package com.salestaxes

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReceiptServiceTest {

    private val taxCalculator: TaxCalculator = mockk()
    private val costCalculator: CostCalculator = mockk()

    @Test
    fun `Build a receipt formatted properly`() {
        every { taxCalculator.computeTotalTaxes(any()) } returns 12.0
        every { taxCalculator.computeTaxAmount(any()) } returns 3.0
        every { costCalculator.computeTotalCost(any()) } returns 92.0
        every { costCalculator.computePriceAfterTaxOf(any()) } returns 20.0
        val items = mapOf(
            Item("chocolate bar", 10.0, false) to 1,
            Item("chair", 30.0, true) to 2,
            Item("imported cigars", 40.0, true) to 1
        )
        val basket = Basket(items)
        val receiptService = ReceiptService(taxCalculator, costCalculator)
        assertThat(receiptService.buildReceiptFor(basket)).isEqualToIgnoringCase("""
            |1 chocolate bar: 20.0
            |2 chair: 20.0
            |1 imported cigars: 20.0
            |Sales taxes: 12.0
            |Total: 92.0
        """.trimMargin())

    }
}

