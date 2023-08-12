package com.loan.loanrequestapp.repository;

import com.loan.loanrequestapp.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanServiceRepository extends JpaRepository<LoanRequest, Long> {
    List<LoanRequest> findByCustomerId(Long customerId);
}
