package com.kgisl.Employee.controller;

import java.util.List;

import com.kgisl.Employee.model.Employee;
import com.kgisl.Employee.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController
 */

@CrossOrigin(origins = "*")
@EnableAspectJAutoProxy(proxyTargetClass = true)

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeservice;

    @PostMapping(value = "/", headers = "Accept=application/json")

    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee actualEmployee = employeeservice.createEmployee(employee);
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(actualEmployee, headers, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<List<Employee>> all() {
        return new ResponseEntity<>(employeeservice.getEmployee(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeByid(@PathVariable("id") Integer id) {
        Employee employee = employeeservice.findbyEmployee(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

@PutMapping(value = "/{id}",headers = "Accept=application/json")
public ResponseEntity<Employee>	updateEmployee(@PathVariable("id")Integer id, @RequestBody Employee currnEmployee){
    Employee employee= employeeservice.updateEmployee(id,currnEmployee);

    
    return new ResponseEntity<>(employee,HttpStatus.OK);
}

@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
public ResponseEntity<Employee> deleteEmployee (@PathVariable("id")Integer id){
    Employee emol = employeeservice.findbyEmployee(id);
    if(emol== null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    employeeservice.deleteEmployeeByid(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

}

}