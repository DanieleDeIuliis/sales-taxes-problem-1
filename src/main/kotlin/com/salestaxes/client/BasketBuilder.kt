package com.salestaxes.client

import com.salestaxes.core.Item

class BasketBuilder(private val inputParser: InputParser) {

    fun build(textualInput: String): Map<Item, Int> {
        val basket = mutableMapOf<Item, Int>()
        textualInput.split("\n").forEach { line ->
            val quantity = inputParser.extractQuantity(line)
            val item = inputParser.extractItemFromInputLine(line)
            basket[item] = quantity
        }
        return basket
    }
}