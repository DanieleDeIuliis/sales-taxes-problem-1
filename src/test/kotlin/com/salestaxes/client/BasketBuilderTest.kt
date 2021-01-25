package com.salestaxes.client

import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasketBuilderTest {
    private val inputParser: InputParser = mockk(relaxed = true)
    @Test
    fun `parse multiple lines input`() {
        val inputAsString = """
            |2 book at 12.49
            |1 music CD at 14.99
            |1 chocolate bar at 0.85
        """.trimMargin()

        val basketBuilder = BasketBuilder(inputParser)
        val basketParsed = basketBuilder.build(inputAsString)

        verify(exactly = 3) { inputParser.extractItemFromInputLine(any()) }
        verify(exactly = 3) { inputParser.extractQuantity(any()) }
        assertThat(basketParsed.size).isEqualTo(3)
    }
}