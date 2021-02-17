package com.salestaxes

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ApplicationTest {

    val inputParser: InputParser = InputParser()
    val taxService: TaxService = TaxService(TaxCalculator(TaxableItemRepository()))
    val console: Console = mockk(relaxed = true)

    @Test
    fun `XXX`() {
        val input = """
            |2 book at 12.49
            |1 music CD at 14.99
            |1 chocolate bar at 0.85""".trimMargin()

        val consoleApp = ConsoleApplication(inputParser, console, taxService)
        consoleApp.process(input)

        verify { console.printProcessedElements(any()) }
    }
}

