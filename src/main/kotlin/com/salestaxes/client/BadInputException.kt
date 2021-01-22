package com.salestaxes.client

class BadInputException: Exception {
    constructor() : super("The input format is not the expected one. Please check the input.")
}