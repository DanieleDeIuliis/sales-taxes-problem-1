package com.salestaxes.client

import com.salestaxes.core.Item

class InputParser {
    fun parse(textualInput: String): Map<Item, Int> {
        val basket = mutableMapOf<Item, Int>()
        textualInput.split("\n").forEach { line ->
            val splitLine = line.split(" ")
            val quantity = extractQuantity(splitLine.first())
            val item = extractItemFromInputLine(splitLine.subList(1,splitLine.size).joinToString(" "))
            basket[item] = quantity
        }
        return basket
    }

    //estrarre un extractor, sotto oppure un builder, sopra?
    private fun extractItemFromInputLine(line: String): Item {
        var isImported = line.contains("imported", ignoreCase = true)
        val splitItem = line.split("at ")
        var name = extractName(splitItem, isImported)
        val price = extractPrice(splitItem.last())
        return Item(name, price, isImported)
    }

    private fun extractName(splitLine: List<String>, isImported: Boolean): String {
        var name = splitLine.first().trim()
        if (name.isEmpty()) {
            throw BadInputException()
        }
        if(isImported) {
            name = name.replace("imported", "",ignoreCase = true).trim()
        }
        return name
    }

    private fun extractQuantity(quantityAsString: String): Int {
        try {
            return quantityAsString.trim().toInt()
        } catch (e: NumberFormatException) {
            throw BadInputException()
        }
    }

    private fun extractPrice(textualPrice: String): Double {
        try {
            return textualPrice.trim().toDouble()
        } catch (e: java.lang.NumberFormatException) {
            throw BadInputException()
        }
    }

}