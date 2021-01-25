package com.salestaxes.client

import com.salestaxes.core.ReceiptService

class ClientOrchestrator(
    private val textualInput: String,
    private val basketBuilder: BasketBuilder,
    private val invoiceService: ReceiptService
) {
    fun computeReceipt(): String {
        val basket = basketBuilder.build(textualInput)
        return invoiceService.buildReceiptFor(basket)
    }

}