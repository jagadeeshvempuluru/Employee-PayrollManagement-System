package com.payroll.repository;

import com.payroll.model.SalaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SalaryDetailsRepository extends JpaRepository<SalaryDetails, Long> {

    Optional<SalaryDetails> findByEmployeeId(Long employeeId);
}