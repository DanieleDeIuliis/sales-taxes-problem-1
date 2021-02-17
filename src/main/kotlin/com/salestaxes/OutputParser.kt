package com.salestaxes

import java.lang.String.format
import java.lang.StringBuilder

class OutputParser {

    fun parseBasket(basket: Basket): String {
        val stringBuilder = StringBuilder()

        basket.items.forEach {
        var imported = ""
            if(it.isImported) {
                imported = "imported "
            }
            stringBuilder.append(format("${it.quantity} $imported${it.name}: %.2f\n", it.grossPrice))
        }

        stringBuilder.append(format("Sales Taxes: %.2f\n", basket.totalTaxes)).append(format("Total: %.2f\n", basket.totalPrice))

        return stringBuilder.toString()
    }

}