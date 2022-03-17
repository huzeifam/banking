package com.example.banking.controller;

import com.example.banking.service.TransactionService;

public class TransactionController {

private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


}
