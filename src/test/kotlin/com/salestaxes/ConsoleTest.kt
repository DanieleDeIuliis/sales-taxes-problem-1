package com.salestaxes

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ConsoleTest {

    private val outputParser: OutputParser = mockk(relaxed = true)

    @Test
    fun `call outputParser when console prints`() {
        val console = Console(outputParser = outputParser)

        val basket = Basket(listOf(BasketItem("book", false, 2, 10.00, 0.00)))
        console.printProcessedElements(basket)

        verify { outputParser.parseBasket(basket) }
    }
}