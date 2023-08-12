package com.loan.loanrequestapp.repository;

import com.loan.loanrequestapp.model.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LoanServiceRepositoryTest {
    @Autowired
    LoanServiceRepository loanServiceRepository;

    @Test
    public void test(){
        LoanRequest loanRequest = new LoanRequest(1, 550, "ROger", 1l);
        loanServiceRepository.save(loanRequest);
        assertEquals(List.of(loanRequest), loanServiceRepository.findByCustomerId(1l));
    }
}
