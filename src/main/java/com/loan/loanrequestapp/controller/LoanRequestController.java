package com.loan.loanrequestapp.controller;

import com.loan.loanrequestapp.constants.LoanAppConstant;
import com.loan.loanrequestapp.exception.LoanServiceException;
import com.loan.loanrequestapp.model.LoanRequest;
import com.loan.loanrequestapp.model.Response;
import com.loan.loanrequestapp.serivce.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/loan-requests")
public class LoanRequestController {
    private  final LoanService loanRequestService;

    @PostMapping
    public ResponseEntity<Object> createLoanRequest(@RequestBody LoanRequest loanRequest) {
       try{
           LoanRequest createdLoanRequest = loanRequestService.createLoanRequest(loanRequest);
           Response response = new Response(true, LoanAppConstant.SUCCESS_MESSAGE, createdLoanRequest);
           return new ResponseEntity<>(response, HttpStatus.CREATED);
       } catch(LoanServiceException le){
           Response response = new Response(true, le.getMessage(), loanRequest);
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
       } catch(Exception e){
           Response response = new Response(true, LoanAppConstant.FAILURE_MESSAGE, loanRequest);
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/total-amount/{customerId}")
    public ResponseEntity<Object> getTotalLoanAmountForCustomer(@PathVariable Long customerId) {
        try {
            double totalLoanAmount = loanRequestService.getTotalLoanAmountForCustomer(customerId);
            String successMessage = totalLoanAmount == 0 ? LoanAppConstant.SUCCESS_MESSAGE_NO_LOAN_AMOUNT : LoanAppConstant.SUCCESS_MESSAGE_LOAN_AMOUNT;
            Response response = new Response(true, successMessage, totalLoanAmount);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e){
            Response response = new Response(true, LoanAppConstant.FAILURE_MESSAGE_LOAN_AMOUNT, customerId);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

