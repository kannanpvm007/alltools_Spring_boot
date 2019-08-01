package com.kgisl.Employee.service;

import java.util.List;

import com.kgisl.Employee.model.Employee;

/**
 * EmployeeService
 */
public interface EmployeeService {

    
    public Employee createEmployee(Employee employee);
    public List<Employee> getEmployee();
	public Employee findbyEmployee(Integer id);
	public Employee updateEmployee(Integer id, Employee employee);
	public void deleteEmployeeByid(Integer id);

}