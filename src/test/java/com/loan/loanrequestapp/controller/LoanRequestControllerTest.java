package com.loan.loanrequestapp.controller;

import com.loan.loanrequestapp.exception.LoanServiceException;
import com.loan.loanrequestapp.model.LoanRequest;
import com.loan.loanrequestapp.serivce.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LoanRequestControllerTest {
    @InjectMocks
    LoanRequestController loanRequestController;
    @Mock
    LoanService loanService;

    @Test
    public void createLoanRequest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        LoanRequest req = new LoanRequest(1, 550, "ROger", 1l);
        ResponseEntity<Object> responseEntity = loanRequestController.createLoanRequest(req);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void createLoanRequestInvalidCustomerId(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        LoanRequest req = new LoanRequest(1, 550, "ROger", null);
        when(loanService.createLoanRequest(req)).thenThrow(new LoanServiceException());
        ResponseEntity<Object> responseEntity = loanRequestController.createLoanRequest(req);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void createLoanRequestInvalidLoanAmount(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        LoanRequest req = new LoanRequest(1, 450, "ROger", 1l);
        when(loanService.createLoanRequest(req)).thenThrow(new LoanServiceException());
        ResponseEntity<Object> responseEntity = loanRequestController.createLoanRequest(req);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getTotalLoanAmount(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Object> responseEntity = loanRequestController.getTotalLoanAmountForCustomer(1l);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getTotalLoanAmountFailure(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(loanService.getTotalLoanAmountForCustomer(1l)).thenThrow(new LoanServiceException());
        ResponseEntity<Object> responseEntity = loanRequestController.getTotalLoanAmountForCustomer(1l);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
