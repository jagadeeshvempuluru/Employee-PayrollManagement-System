package com.payroll.controller;

import com.payroll.model.PayrollHistory;
import com.payroll.model.SalaryDetails;
import com.payroll.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin(origins = "*")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    // POST process monthly payroll for all employees
    @PostMapping("/process/{month}/{year}")
    public ResponseEntity<String> processPayroll(
            @PathVariable int month,
            @PathVariable int year) {
        String result = payrollService.processMonthlyPayroll(month, year);
        return ResponseEntity.ok(result);
    }

    // GET payroll history for one employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollHistory>> getPayrollByEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollByEmployee(employeeId));
    }

    // GET payroll records for specific month and year
    @GetMapping("/{month}/{year}")
    public ResponseEntity<List<PayrollHistory>> getPayrollByMonthYear(
            @PathVariable int month,
            @PathVariable int year) {
        return ResponseEntity.ok(payrollService.getPayrollByMonthYear(month, year));
    }

    // POST calculate and save salary details for one employee
    @PostMapping("/salary/{employeeId}")
    public ResponseEntity<SalaryDetails> saveSalaryDetails(
            @PathVariable Long employeeId) {
        SalaryDetails details = payrollService.saveSalaryDetails(employeeId);
        return ResponseEntity.ok(details);
    }
}