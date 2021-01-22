package com.salestaxes

class TaxCalculator {
    fun computeTaxAmount(item: Item): Double {
        var taxPercentage = 0
        if(item.isImported) {
            taxPercentage += 5
        }
        if(item.isTaxable) {
            taxPercentage += 10
        }
        return taxPercentage.div(100.0) * item.price
    }

}