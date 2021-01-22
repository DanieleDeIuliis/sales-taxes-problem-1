package com.salestaxes.client

import com.salestaxes.core.Item

class InputParser {
    fun parse(textualInput: String): Map<Item, Int> {
        val basket = mutableMapOf<Item, Int>()
        textualInput.split("\n").forEach { line ->
            val splitLine = line.split(" ")
            val quantity = getQuantity(splitLine.first())
            val item = extractItemFromInputLine(splitLine.subList(1,splitLine.size).joinToString(" "))
            basket[item] = quantity
        }
        return basket
    }

    private fun extractItemFromInputLine(line: String): Item {
        val splitItem = line.split("at")
        val nameList = splitItem.first().trim().split(" ").filter { it.isNotEmpty() }
        if (nameList.isEmpty()) {
            throw BadInputException()
        }
        val (rest, match) = nameList.partition { it.equals("imported") }
        val isImported = rest.isNotEmpty()
        val name = match.joinToString(" ")
        val price = getPrice(splitItem.last())
        return Item(name, price, isImported)
    }

    private fun getQuantity(quantityAsString: String): Int {
        try {
            return quantityAsString.trim().toInt()
        } catch (e: NumberFormatException) {
            throw BadInputException()
        }
    }

    private fun getPrice(textualPrice: String): Double {
        try {
            return textualPrice.trim().toDouble()
        } catch (e: java.lang.NumberFormatException) {
            throw BadInputException()
        }
    }

}