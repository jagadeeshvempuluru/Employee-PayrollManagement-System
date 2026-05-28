package com.payroll.controller;

import com.payroll.model.Employee;
import com.payroll.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    // GET ALL EMPLOYEES
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return employeeService
                .getEmployeeById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
    }

    // ADD EMPLOYEE
    @PostMapping
    public Employee addEmployee(
            @RequestBody Employee employee) {

        return employeeService.addEmployee(employee);
    }

    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee Deleted Successfully";
    }
}