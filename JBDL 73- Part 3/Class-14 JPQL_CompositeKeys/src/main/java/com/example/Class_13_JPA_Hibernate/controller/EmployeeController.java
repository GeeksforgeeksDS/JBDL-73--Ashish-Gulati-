package com.example.Class_13_JPA_Hibernate.controller;

import com.example.Class_13_JPA_Hibernate.dto.CreateEmployeeRequest;
import com.example.Class_13_JPA_Hibernate.model.Employee;
import com.example.Class_13_JPA_Hibernate.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController                     // Controller vs RestController
@RequestMapping("/employee")        // RequestMapping for this controller api's
public class EmployeeController {

    // Field (DI)
    @Autowired
    EmployeeService employeeService;

    // create api
    /*
        params CreateEmployeeRequest -> dto
        return ResponseEntity<String> containing empId;
     */
    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
                            // Why DTO's and not Model itself
                            // Validation

        // Request transformation (dto to model)
        Employee employee = createEmployeeRequest.to();

        // Call the service
        Integer empID = employeeService.saveEmployee(employee);

        // Build the Response entity (Response transformation)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Id : " + empID);
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();

        return ResponseEntity
                .ok("Employees: " + employeeList.toString());

    }





}
