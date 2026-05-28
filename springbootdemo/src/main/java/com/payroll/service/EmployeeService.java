package com.payroll.service;

import com.payroll.model.Employee;
import com.payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Add new employee
    public Employee addEmployee(Employee employee) {

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException(
                    "Email already exists: " + employee.getEmail()
            );
        }

        return employeeRepository.save(employee);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found with id: " + id
                        ));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhone(updatedEmployee.getPhone());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());
        existingEmployee.setBasicSalary(updatedEmployee.getBasicSalary());
        existingEmployee.setStatus(updatedEmployee.getStatus());

        return employeeRepository.save(existingEmployee);
    }

    // Delete employee
    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {

            throw new RuntimeException(
                    "Employee not found with id: " + id
            );
        }

        employeeRepository.deleteById(id);
    }

    // Get employees by department
    public List<Employee> getByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    // Get active employees
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findByStatus("ACTIVE");
    }

    // Get total employee count
    public long getTotalEmployees() {
        return employeeRepository.count();
    }

    // Get inactive employee count
    public long getInactiveEmployeesCount() {

        long totalEmployees = employeeRepository.count();

        long activeEmployees =
                employeeRepository.findByStatus("ACTIVE").size();

        return totalEmployees - activeEmployees;
    }
}