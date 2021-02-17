package com.salestaxes

import io.mockk.verify
import org.junit.jupiter.api.Test

class ApplicationTest {

    val inputParser: InputParser = InputParser()
    val taxService: TaxService = TaxService(TaxCalculator(TaxableItemRepository()))
    val console: Console = Console()

    @Test
    fun `XXX`() {
        val input = """
            |2 book at 12.49
            |1 music CD at 14.99
            |1 chocolate bar at 0.85""".trimMargin()
        val basket = Basket(listOf(
            BasketItem("book", false, 2, 24.98, 0.00),
            BasketItem("music CD", false, 1, 16.49, 1.50),
            BasketItem("chocolate bar", false, 1, 0.85, 0.00)
        ))

        val consoleApp = ConsoleApplication(inputParser, console, taxService)
        consoleApp.process(input)

        verify { console.printProcessedElements(basket) }
    }
}

