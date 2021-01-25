package com.salestaxes.client

import com.salestaxes.core.CostCalculator
import com.salestaxes.core.ReceiptService
import com.salestaxes.core.TaxCalculator
import com.salestaxes.store.TaxableInMemoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AcceptanceTest {
    @Test
    fun `output one test`() {
        val textualInput = """
            |2 book at 12.49
            |1 music CD at 14.99
            |1 chocolate bar at 0.85
        """.trimMargin()
        val inputParser = InputParser()
        val basketBuilder = BasketBuilder(inputParser)
        val taxCalculator = TaxCalculator(TaxableInMemoryRepository())
        val invoiceService = ReceiptService(taxCalculator, CostCalculator(taxCalculator))
        val clientOrchestrator = ClientOrchestrator(textualInput, basketBuilder, invoiceService)

        val receipt = clientOrchestrator.computeReceipt()

        assertThat(receipt).isEqualTo("""
            |2 book: 24.98
            |1 music CD: 16.49
            |1 chocolate bar: 0.85
            |Sales Taxes: 1.50
            |Total: 42.32
        """.trimMargin())

    }
}

