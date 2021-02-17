package com.salestaxes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TaxableItemRepositoryTest {

    @Test
    fun `returns true when a taxable item name is passed`() {
        val taxableItemRepository = TaxableItemRepository()

        assertTrue(taxableItemRepository.isTaxable("cellphone"))
    }

    @Test
    fun `returns false when a non taxable item name is passed`() {
        val taxableItemRepository = TaxableItemRepository()

        assertFalse(taxableItemRepository.isTaxable("chocolate bar"))
    }
}