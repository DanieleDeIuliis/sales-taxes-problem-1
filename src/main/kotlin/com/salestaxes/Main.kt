package com.salestaxes

class Main {
    companion object {
        fun computeTaxAndGetOutputBasket(input: String): String {
            val taxService = TaxService(TaxCalculator(TaxableItemRepository()))
            val basket = taxService.computeTax(InputParser().parse(input))
            return OutputParser().parseBasket(basket)
        }
    }
}