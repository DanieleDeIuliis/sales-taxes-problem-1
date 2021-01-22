package com.salestaxes.client

import com.salestaxes.core.ReceiptService

class ClientOrchestrator(
    private val textualInput: String,
    private val inputParser: InputParser,
    private val invoiceService: ReceiptService
) {
    fun computeReceipt(): String {
        val basket = inputParser.parse(textualInput)
        return invoiceService.buildReceiptFor(basket)
    }

}