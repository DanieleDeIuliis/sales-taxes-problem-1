package com.salestaxes

class InputParser {
    fun parse(input: String): List<Item> {
        val splitInput = input.split("\n")
        return splitInput.map { line -> parseItemFrom(line) }
    }

    private fun parseItemFrom(line: String): Item {
        val pattern = "(\\d+) (.*) at (\\d+\\.\\d{2})".toRegex()
        val resultGroups = pattern.find(line)?.groups
        val quantity = resultGroups?.get(1)?.value ?: null
        val price = resultGroups?.get(3)?.value ?: null
        return Item("", true, getPriceOrThrow(price), getQuantityOrThrow(quantity))
    }

    private fun getQuantityOrThrow(quantity: String?): Int {
        try {
            return quantity!!.toInt()
        } catch (e: Exception) {
            throw InvalidInputException()
        }
    }

    private fun getPriceOrThrow(price: String?): Double {
        try {
            return price!!.toDouble()
        } catch (e: Exception) {
            throw InvalidInputException()
        }
    }

}