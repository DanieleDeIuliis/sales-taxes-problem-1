package com.salestaxes

class Console(private val outputParser: OutputParser) {
    fun printProcessedElements(basket: Basket) {
        println(outputParser.parseBasket(basket))
    }

}