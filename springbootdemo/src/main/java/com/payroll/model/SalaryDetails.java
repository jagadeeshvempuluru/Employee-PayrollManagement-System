package com.payroll.model;

import jakarta.persistence.*;

@Entity
@Table(name = "salary_details")
public class SalaryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private Double basicPay;
    private Double hra;
    private Double da;
    private Double pfDeduction;
    private Double taxDeduction;
    private Double netSalary;

    public SalaryDetails() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Double getBasicPay() { return basicPay; }
    public void setBasicPay(Double basicPay) { this.basicPay = basicPay; }

    public Double getHra() { return hra; }
    public void setHra(Double hra) { this.hra = hra; }

    public Double getDa() { return da; }
    public void setDa(Double da) { this.da = da; }

    public Double getPfDeduction() { return pfDeduction; }
    public void setPfDeduction(Double pfDeduction) { this.pfDeduction = pfDeduction; }

    public Double getTaxDeduction() { return taxDeduction; }
    public void setTaxDeduction(Double taxDeduction) { this.taxDeduction = taxDeduction; }

    public Double getNetSalary() { return netSalary; }
    public void setNetSalary(Double netSalary) { this.netSalary = netSalary; }
}