package com.loan.loanrequestapp.serivce.impl;

import com.loan.loanrequestapp.constants.LoanAppConstant;
import com.loan.loanrequestapp.exception.LoanServiceException;
import com.loan.loanrequestapp.model.LoanRequest;
import com.loan.loanrequestapp.repository.LoanServiceRepository;
import com.loan.loanrequestapp.serivce.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanServiceRepository loanServiceRepository;

    @Override
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        validateRequest(loanRequest);
        return loanServiceRepository.save(loanRequest);
    }

    @Override
    public double getTotalLoanAmountForCustomer(Long customerId) {
        List<LoanRequest> loanRequests = loanServiceRepository.findByCustomerId(customerId);
        return loanRequests.stream().mapToDouble(LoanRequest::getLoanAmount).sum();
    }

    private void validateRequest(LoanRequest loanRequest){
        if(loanRequest == null || Objects.isNull(loanRequest.getCustomerId())){
            throw new LoanServiceException(LoanAppConstant.INVALID_CUSTOMER_ID);
        }
        if(loanRequest.getLoanAmount() < LoanAppConstant.LOWER_LIMIT || loanRequest.getLoanAmount() > LoanAppConstant.UPPER_LIMIT){
            throw new LoanServiceException(LoanAppConstant.INVALID_LOAN_AMOUNT);
        }
        return;
    }
}
