package com.salestaxes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AcceptanceTest {
    @Test
    fun `prova prova`() {
        val input = """
            |2 book at 12.49
            |1 music CD at 14.99
            |1 chocolate bar at 0.85""".trimMargin()
        val output = """
            |2 book: 24.98
            |1 music CD: 16.49
            |1 chocolate bar: 0.85
            |Sales Taxes: 1.50
            |Total: 42.32
        |""".trimMargin()
        assertThat(Main.computeTaxAndGetOutputBasket(input)).isEqualTo(output)
    }
}