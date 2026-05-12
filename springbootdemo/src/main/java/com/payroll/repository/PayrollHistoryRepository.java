package com.payroll.repository;

import com.payroll.model.PayrollHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollHistoryRepository extends JpaRepository<PayrollHistory, Long> {

    List<PayrollHistory> findByEmployeeId(Long employeeId);

    Optional<PayrollHistory> findByEmployeeIdAndMonthAndYear(
            Long employeeId, Integer month, Integer year);

    List<PayrollHistory> findByMonthAndYear(Integer month, Integer year);
}