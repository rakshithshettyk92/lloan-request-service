package com.loan.loanrequestapp.service;

import com.loan.loanrequestapp.exception.LoanServiceException;
import com.loan.loanrequestapp.model.LoanRequest;
import com.loan.loanrequestapp.repository.LoanServiceRepository;
import com.loan.loanrequestapp.serivce.impl.LoanServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoanServiceImplTest {
    @Mock
    LoanServiceRepository loanServiceRepository;
    @InjectMocks
    LoanServiceImpl loanService;


    @Test
    void verifyCreateLoanRequestWhenInvalidLoanAmount(){
        LoanRequest req = new LoanRequest(1, 450, "ROger", 1l);
        assertThrows(LoanServiceException.class, () -> loanService.createLoanRequest(req));
    }

    @Test
    void verifyCreateLoanRequestWhenInvalidCustomerId(){
        LoanRequest req = new LoanRequest(1, 550, "ROger", null);
        assertThrows(LoanServiceException.class, () -> loanService.createLoanRequest(req));
    }

    @Test
    void verifyCreateLoanRequestWhenValidRequest(){
        LoanRequest req = new LoanRequest(1, 550, "ROger", 1l);
        loanService.createLoanRequest(req);
        verify(loanServiceRepository, times(1)).save(req);
    }

    @Test
    void verifyGetTotalLoanAmount(){
        when(loanServiceRepository.findByCustomerId(1l)).thenReturn(List.of(new LoanRequest(1, 550, "ROger", 1l)));
        double res = loanService.getTotalLoanAmountForCustomer(1l);
        assertEquals(550, res);
    }
}
