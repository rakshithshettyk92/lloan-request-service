package com.loan.loanrequestapp.exception;

public class LoanServiceException extends RuntimeException{
    public LoanServiceException(String message){ super(message);}
    public LoanServiceException(){super();}
}
