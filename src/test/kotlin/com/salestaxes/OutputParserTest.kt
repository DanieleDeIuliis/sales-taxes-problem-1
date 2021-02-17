package com.salestaxes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutputParserTest {
    @Test
    fun `prints total and taxes lines for a single item input`() {
        val outputParser = OutputParser()
        val basket = Basket(listOf(BasketItem("book", false, 1, 10.00, 1.50)), 10.00, 1.50)

        val parsedOutput = outputParser.parseBasket(basket)

        assertThat(parsedOutput).contains("Sales Taxes: 1.50")
        assertThat(parsedOutput).contains("Total: 10.00")

    }

    @Test
    fun `prints the proper output for a single item line`() {
        val outputParser = OutputParser()
        val basket = Basket(listOf(BasketItem("book", false, 1, 10.00, 1.50)), 10.00, 1.50)

        val parsedOutput = outputParser.parseBasket(basket)

        assertThat(parsedOutput).contains("1 book: 10.00")
    }

    @Test
    fun `prints back imported for an imported item`() {
        val outputParser = OutputParser()
        val basket = Basket(listOf(BasketItem("chocolate bar", true, 2, 5.05, 2.05))
            , 10.00, 1.50)

        val parsedOutput = outputParser.parseBasket(basket)

        assertThat(parsedOutput).contains("2 imported chocolate bar: 5.05")
    }

    @Test
    fun `prints the proper output for a mulltiple item basket`() {
        val outputParser = OutputParser()
        val basket = Basket(listOf(
            BasketItem("book", false, 1, 10.00, 1.50),
            BasketItem("chocolate bar", true, 2, 5.05, 2.05)
        ), 10.00, 1.50)

        val parsedOutput = outputParser.parseBasket(basket)

        assertThat(parsedOutput).contains("1 book: 10.00")
        assertThat(parsedOutput).contains("2 imported chocolate bar: 5.05")
    }
}