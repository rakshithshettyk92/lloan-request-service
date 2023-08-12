package com.loan.loanrequestapp.serivce;

import com.loan.loanrequestapp.model.LoanRequest;

public interface LoanService {

    LoanRequest createLoanRequest(LoanRequest loanRequest);
    double getTotalLoanAmountForCustomer(Long customerId);
}
