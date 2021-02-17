package com.salestaxes

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TaxServiceTest {

    private val taxCalculator: TaxCalculator = mockk(relaxed = true)

    @Test
    fun `call computeTaxOf for each item in the list`() {
        val aBook = Item("book", true, 10.50, 1)
        val items = listOf(aBook, aBook)
        val taxService = TaxService(taxCalculator = taxCalculator)

        val basket = taxService.computeTax(items)

        verify(exactly = 2) { taxCalculator.computeTaxFor(aBook) }
    }

    @Test
    fun `create the proper amount of basket items from a list of normal items`() {
        val aBook = Item("book", true, 10.50, 1)
        val items = listOf(aBook, aBook)
        val taxService = TaxService(taxCalculator = taxCalculator)

        val basket = taxService.computeTax(items)

        assertThat(basket.items.size).isEqualTo(2)
    }

    @Test
    fun `creates a basket with one item with the same name, isImported and quantity`() {
        val aBook = Item("book", true, 10.50, 1)
        val items = listOf(aBook)
        val taxService = TaxService(taxCalculator = taxCalculator)

        val basketItem = taxService.computeTax(items).items.first()

        assertThat(basketItem.name).isEqualTo("book")
        assertThat(basketItem.isImported).isEqualTo(true)
        assertThat(basketItem.quantity).isEqualTo(1)
    }

    @Test
    fun `creates a basket with one item having the proper total amount of tax`() {
        every { taxCalculator.computeTaxFor(any()) } returns 5.25
        val aBook = Item("book", true, 10.50, 2)
        val items = listOf(aBook)
        val taxService = TaxService(taxCalculator = taxCalculator)

        val basketItem = taxService.computeTax(items).items.first()

        assertThat(basketItem.totalTaxAmount).isEqualTo(10.50)
    }

    @Test
    fun `creates a basket with one item having the proper gross price`() {
        every { taxCalculator.computeTaxFor(any()) } returns 5.25
        val aBook = Item("book", true, 10.50, 2)
        val items = listOf(aBook)
        val taxService = TaxService(taxCalculator = taxCalculator)

        val basketItem = taxService.computeTax(items).items.first()

        assertThat(basketItem.grossPrice).isEqualTo(31.50)
    }

    @Test
    fun `creates a basket with one item having the proper total cost`() {
        every { taxCalculator.computeTaxFor(any()) } returns 5.25
        val aBook = Item("book", true, 10.50, 3)
        val items = listOf(aBook)
        val taxService = TaxService(taxCalculator = taxCalculator)

        val basket = taxService.computeTax(items)

        assertThat(basket.totalPrice).isEqualTo(47.25)
    }
}

