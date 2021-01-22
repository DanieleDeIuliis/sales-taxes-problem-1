package com.salestaxes.client

import com.salestaxes.core.Item
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertTrue

class InputParserTest {

    @Test
    fun `parse single input missing quantity`() {
        val inputParser = InputParser()
        val inputAsString = "book at 12.49"
        assertThrows<BadInputException> { inputParser.parse(inputAsString) }
    }

    @Test
    fun `parse single input missing name`() {
        val inputParser = InputParser()
        val inputAsString = "2 at 12.49"
        assertThrows<BadInputException> { inputParser.parse(inputAsString) }
    }

    @Test
    fun `parse single input missing price`() {
        val inputParser = InputParser()
        val inputAsString = "2 book at"
        assertThrows<BadInputException> { inputParser.parse(inputAsString) }
    }

    @Test
    fun `parse single input imported item`() {
        val inputParser = InputParser()
        val inputAsString = "2 imported book at 12.99"
        val firstItem = inputParser.parse(inputAsString).keys.first()
        assertThat(firstItem.name).isEqualTo("book")
        assertTrue(firstItem.isImported)
    }

    @Test
    fun `parse single line input`() {
        val inputParser = InputParser()
        val inputAsString = "2 book at 12.49"
        val basketParsed = inputParser.parse(inputAsString)

        assertThat(basketParsed.size).isEqualTo(1)
        val aBook = Item("book", 12.49, false)
        assertThat(basketParsed.keys.find { it.name.equals("book", ignoreCase = true) }).isEqualTo(aBook)
        assertThat(basketParsed[aBook]).isEqualTo(2)
    }

    @Test
    fun `parse multiple lines input`() {
        val inputParser = InputParser()
        val inputAsString = """
            |2 book at 12.49
            |1 music CD at 14.99
            |1 chocolate bar at 0.85
        """.trimMargin()
        val basketParsed = inputParser.parse(inputAsString)

        assertThat(basketParsed.size).isEqualTo(3)
    }
}

