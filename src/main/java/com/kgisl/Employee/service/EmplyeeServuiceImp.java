package com.kgisl.Employee.service;

import java.util.List;

import com.kgisl.Employee.model.Employee;
import com.kgisl.Employee.repository.EmplyeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EmplyeeServuiceImp
 */
@Service
public class EmplyeeServuiceImp implements EmployeeService {

    @Autowired
    private EmplyeeRepository emplyeeRepository;


    @Override
    public Employee createEmployee(Employee employee) {
        return emplyeeRepository.save(employee);
    }
    



    @Override
	public List<Employee> getEmployee() {
        System.out.println(emplyeeRepository.findAll());
        return emplyeeRepository.findAll();
	}

    @Override
    public Employee findbyEmployee(Integer id) {
     return emplyeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
       }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee emp= emplyeeRepository.getOne(id);
        emp.setName(employee.getName());
        return emplyeeRepository.save(emp);
    }

    @Override
    public void deleteEmployeeByid(Integer id) {
emplyeeRepository.deleteById(id);
    }

  

  
}