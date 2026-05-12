package com.payroll.service;

import com.payroll.model.Employee;
import com.payroll.model.PayrollHistory;
import com.payroll.model.SalaryDetails;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.PayrollHistoryRepository;
import com.payroll.repository.SalaryDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PayrollHistoryRepository payrollHistoryRepository;

    @Autowired
    private SalaryDetailsRepository salaryDetailsRepository;

    // ── Core Salary Calculation Engine ──────────────────────

    public double calculateHRA(double basicSalary) {
        return basicSalary * 0.40; // 40% of basic
    }

    public double calculateDA(double basicSalary) {
        return basicSalary * 0.20; // 20% of basic
    }

    public double calculatePF(double basicSalary) {
        return basicSalary * 0.12; // 12% of basic
    }

    public double calculateTax(double grossSalary) {
        // Annual salary for tax calculation
        double annualSalary = grossSalary * 12;
        double tax = 0;

        if (annualSalary <= 250000) {
            tax = 0;
        } else if (annualSalary <= 500000) {
            tax = (annualSalary - 250000) * 0.05;
        } else if (annualSalary <= 1000000) {
            tax = 12500 + (annualSalary - 500000) * 0.20;
        } else {
            tax = 112500 + (annualSalary - 1000000) * 0.30;
        }

        // Return monthly tax
        return tax / 12;
    }

    public double calculateGrossSalary(double basicSalary) {
        return basicSalary
                + calculateHRA(basicSalary)
                + calculateDA(basicSalary);
    }

    public double calculateNetSalary(double basicSalary) {
        double gross = calculateGrossSalary(basicSalary);
        double pf = calculatePF(basicSalary);
        double tax = calculateTax(gross);
        return gross - pf - tax;
    }

    // ── Process Monthly Payroll ──────────────────────────────

    public String processMonthlyPayroll(int month, int year) {
        List<Employee> activeEmployees = employeeRepository.findByStatus("ACTIVE");
        int count = 0;

        for (Employee employee : activeEmployees) {
            // Skip if already processed
            boolean alreadyProcessed = payrollHistoryRepository
                    .findByEmployeeIdAndMonthAndYear(employee.getId(), month, year)
                    .isPresent();

            if (alreadyProcessed) continue;

            double basic = employee.getBasicSalary() != null ? employee.getBasicSalary() : 0;
            double gross = calculateGrossSalary(basic);
            double pf = calculatePF(basic);
            double tax = calculateTax(gross);
            double net = gross - pf - tax;

            PayrollHistory history = new PayrollHistory();
            history.setEmployee(employee);
            history.setMonth(month);
            history.setYear(year);
            history.setGrossPay(gross);
            history.setTotalDeductions(pf + tax);
            history.setNetPay(net);
            history.setStatus("PAID");
            history.setGeneratedDate(LocalDate.now());

            payrollHistoryRepository.save(history);
            count++;
        }

        return "Payroll processed for " + count + " employees.";
    }

    // ── Save Salary Details ──────────────────────────────────

    public SalaryDetails saveSalaryDetails(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        double basic = employee.getBasicSalary() != null ? employee.getBasicSalary() : 0;

        SalaryDetails details = salaryDetailsRepository
                .findByEmployeeId(employeeId)
                .orElse(new SalaryDetails());

        details.setEmployee(employee);
        details.setBasicPay(basic);
        details.setHra(calculateHRA(basic));
        details.setDa(calculateDA(basic));
        details.setPfDeduction(calculatePF(basic));
        details.setTaxDeduction(calculateTax(calculateGrossSalary(basic)));
        details.setNetSalary(calculateNetSalary(basic));

        return salaryDetailsRepository.save(details);
    }

    // ── Get Payroll History ──────────────────────────────────

    public List<PayrollHistory> getPayrollByEmployee(Long employeeId) {
        return payrollHistoryRepository.findByEmployeeId(employeeId);
    }

    public List<PayrollHistory> getPayrollByMonthYear(int month, int year) {
        return payrollHistoryRepository.findByMonthAndYear(month, year);
    }
}