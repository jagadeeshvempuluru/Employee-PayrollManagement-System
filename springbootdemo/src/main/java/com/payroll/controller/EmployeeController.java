package com.payroll.controller;

import com.payroll.model.Employee;
import com.payroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
//@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Dashboard Page
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long totalEmployees = employeeService.getTotalEmployees();

        long activeEmployees = employeeService.getActiveEmployees().size();

        long inactiveEmployees =
                totalEmployees - activeEmployees;

        List<Employee> employees =
                employeeService.getAllEmployees();

        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("activeEmployees", activeEmployees);
        model.addAttribute("inactiveEmployees", inactiveEmployees);
        model.addAttribute("employees", employees);

        return "dashboard";
    }

    // GET all employees page
    @GetMapping
    public String getAllEmployees(Model model) {

        model.addAttribute(
                "employees",
                employeeService.getAllEmployees()
        );

        return "employees";
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public String getEmployeeById(
            @PathVariable Long id,
            Model model) {

        Employee employee = employeeService
                .getEmployeeById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found"));

        model.addAttribute("employee", employee);

        return "employee-details";
    }

    // Show Add Employee Form
    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("employee", new Employee());

        return "add-employee";
    }

    // Save Employee
    @PostMapping("/save")
    public String addEmployee(
            @ModelAttribute Employee employee) {

        employeeService.addEmployee(employee);

        return "redirect:/employees";
    }

    // Show Update Form
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Employee employee = employeeService
                .getEmployeeById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found"));

        model.addAttribute("employee", employee);

        return "edit-employee";
    }

    // Update Employee
    @PostMapping("/update/{id}")
    public String updateEmployee(
            @PathVariable Long id,
            @ModelAttribute Employee employee) {

        employeeService.updateEmployee(id, employee);

        return "redirect:/employees";
    }

    // Delete Employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }

    // Employees by Department
    @GetMapping("/department/{department}")
    public String getByDepartment(
            @PathVariable String department,
            Model model) {

        model.addAttribute(
                "employees",
                employeeService.getByDepartment(department)
        );

        model.addAttribute("department", department);

        return "department-employees";
    }

    // Active Employees
    @GetMapping("/active")
    public String getActiveEmployees(Model model) {

        model.addAttribute(
                "employees",
                employeeService.getActiveEmployees()
        );

        return "active-employees";
    }
}