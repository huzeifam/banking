package com.example.banking.model;

import java.util.List;

public class CustomerAccountResponse {

    private List<CustomerResponse> theCustomer;
    private List<AccountResponse> theAccounts;

    public CustomerAccountResponse(List<CustomerResponse> theCustomer, List<AccountResponse> theAccounts) {
        this.theCustomer = theCustomer;
        this.theAccounts = theAccounts;
    }

    public List<CustomerResponse> getTheCustomer() {
        return theCustomer;
    }

    public List<AccountResponse> getTheAccounts() {
        return theAccounts;
    }
}

