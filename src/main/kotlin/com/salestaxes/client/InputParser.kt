package com.salestaxes.client

import com.salestaxes.core.Item

class InputParser {

    fun extractItemFromInputLine(line: String): Item {
        val lineWithoutQuantity = line.substring(line.indexOfFirst { it == ' ' })
        var isImported = lineWithoutQuantity.contains("imported", ignoreCase = true)
        val splitItem = lineWithoutQuantity.split("at ")
        var name = extractName(splitItem, isImported)
        val price = extractPrice(splitItem.last())
        return Item(name, price, isImported)
    }

    fun extractQuantity(lineInput: String): Int {
        try {
            return lineInput.split(" ").first().trim().toInt()
        } catch (e: NumberFormatException) {
            throw BadInputException()
        }
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

    private fun extractPrice(textualPrice: String): Double {
        try {
            return textualPrice.trim().toDouble()
        } catch (e: java.lang.NumberFormatException) {
            throw BadInputException()
        }
    }

}