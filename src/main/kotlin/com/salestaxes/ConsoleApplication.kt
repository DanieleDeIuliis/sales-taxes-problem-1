package com.salestaxes

class ConsoleApplication(
    private val inputParser: InputParser,
    private val console: Console,
    private val taxService: TaxService
) {
    fun process(input: String) {
        val items: List<Item> = inputParser.parse(input)

        val taxedBasket: Basket = taxService.computeTax(items)

        console.printProcessedElements(taxedBasket)
    }

}