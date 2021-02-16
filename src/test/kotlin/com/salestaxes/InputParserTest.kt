package com.salestaxes

import org.assertj.core.api.Assertions.`in`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputParserTest {


    @Test
    fun `returns the correct price when a single line input is passed`() {
        val input = "2 imported book at 12.49"
        val inputParser = InputParser()

        val parsedItems = inputParser.parse(input)

        assertThat(parsedItems).isNotEmpty
        val item = Item(name = "book", isImported = true, price = 12.49, quantity = 2)
        assertThat(parsedItems.first().price).isEqualTo(item.price)
    }

    @Test
    fun `throws InvalidInputException when there is no price`() {
        val input = "2 imported book at "
        val inputParser = InputParser()

        assertThrows<InvalidInputException> { inputParser.parse(input) }
    }

    @Test
    fun `throws InvalidInputException when there is an invalid price`() {
        val input = "2 imported book at twelve"
        val inputParser = InputParser()

        assertThrows<InvalidInputException> { inputParser.parse(input) }
    }

    @Test
    fun `throws InvalidInputException when there is a partial decimal price`() {
        val input = "2 imported book at 12."
        val inputParser = InputParser()

        assertThrows<InvalidInputException> { inputParser.parse(input) }
    }

    @Test
    fun `throws InvalidInputException when there is a partial int price `() {
        val input = "2 imported book at .02"
        val inputParser = InputParser()

        assertThrows<InvalidInputException> { inputParser.parse(input) }
    }

    @Test
    fun `returns the correct quantity when the input is valid`() {
        val input = "2 imported book at 12.49"
        val inputParser = InputParser()

        val parsedItems = inputParser.parse(input)

        assertThat(parsedItems).isNotEmpty
        val item = Item(name = "book", isImported = true, price = 12.49, quantity = 2)
        assertThat(parsedItems.first().quantity).isEqualTo(item.quantity)
    }

    @Test
    fun `throws InvalidInputException when there is no quantity`() {
        val input = "imported book at 12.02"
        val inputParser = InputParser()

        assertThrows<InvalidInputException> { inputParser.parse(input) }
    }

    @Test
    fun `throws InvalidInputException when there is an invalid quantity`() {
        val input = "2a imported book at 12.02"
        val inputParser = InputParser()

        assertThrows<InvalidInputException> { inputParser.parse(input) }
    }

    @Test
    fun `returns the correct isImported when the input is valid`() {
        val input = "2 imported book at 12.49"
        val inputParser = InputParser()

        val parsedItems = inputParser.parse(input)

        assertThat(parsedItems).isNotEmpty
        val item = Item(name = "book", isImported = true, price = 12.49, quantity = 2)
        assertThat(parsedItems.first().isImported).isEqualTo(item.isImported)
    }

    @Test
    fun `returns the correct isImported when the input is a not imported valid item`() {
        val input = "2 book at 12.49"
        val inputParser = InputParser()

        val parsedItems = inputParser.parse(input)

        assertThat(parsedItems).isNotEmpty
        val item = Item(name = "book", isImported = false, price = 12.49, quantity = 2)
        assertThat(parsedItems.first().isImported).isEqualTo(item.isImported)
    }

    @Test
    fun `throws InvalidInputException when the input does not contain a name`() {
        val input = "2 at 12.49"
        val inputParser = InputParser()

        assertThrows<InvalidInputException> { inputParser.parse(input) }
    }

    @Test
    fun `returns the correct name when the input is valid`() {
        val input = "2 imported book at 12.49"
        val inputParser = InputParser()

        val parsedItems = inputParser.parse(input)

        assertThat(parsedItems).isNotEmpty
        val item = Item(name = "book", isImported = true, price = 12.49, quantity = 2)
        assertThat(parsedItems.first().name).isEqualTo(item.name)
    }

    @Test
    fun `returns the correct name when the input is a valid not imported item`() {
        val input = "2 wonderful book at 12.49"
        val inputParser = InputParser()

        val parsedItems = inputParser.parse(input)

        assertThat(parsedItems).isNotEmpty
        val item = Item(name = "wonderful book", isImported = true, price = 12.49, quantity = 2)
        assertThat(parsedItems.first().name).isEqualTo(item.name)
    }

    @Test
    fun `returns a properly formatted item when a single line input is passed`() {
        val input = "2 imported book at 12.49"
        val inputParser = InputParser()

        val parsedItems = inputParser.parse(input)

        assertThat(parsedItems).isNotEmpty
        assertThat(parsedItems.first()).isEqualTo(Item(name = "book", isImported = true, price = 12.49, quantity = 2 ))
    }
}