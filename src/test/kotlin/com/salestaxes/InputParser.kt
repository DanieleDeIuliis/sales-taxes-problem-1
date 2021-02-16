package com.salestaxes

class InputParser {
    fun parse(input: String): List<Item> {
        val splitInput = input.split("\n")
        return splitInput.map { line -> parseItemFrom(line) }
    }

    private fun parseItemFrom(line: String): Item {
        val pattern = "(\\d+) (.*) at (\\d+\\.\\d{2})".toRegex()
        val resultGroups = pattern.find(line)?.groups
        val quantity = resultGroups?.get(1)?.value
        val name = resultGroups?.get(2)?.value
        val price = resultGroups?.get(3)?.value
        return Item(getNameOrThrow(name), getIsImportedOrThrow(name), getPriceOrThrow(price), getQuantityOrThrow(quantity))
    }

    private fun getNameOrThrow(name: String?): String {
        try {
            return name!!.split(" ").filter { !it.equals("imported", ignoreCase = true) }.joinToString(" ")
        } catch (e: Exception) {
            throw InvalidInputException()
        }
    }

    private fun getIsImportedOrThrow(name: String?): Boolean {
        try {
            return name!!.contains("imported", ignoreCase = true)
        } catch (e: Exception) {
            throw InvalidInputException()
        }
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
