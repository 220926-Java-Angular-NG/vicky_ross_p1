package org.financeDistro.Services;

import org.financeDistro.Models.Employees;
import org.financeDistro.Repos.EmployeeRepo;

import java.util.List;

public class EmployeeServices {
    private EmployeeRepo employeeRepo;

    public EmployeeServices(){ employeeRepo = new EmployeeRepo(); }

    public EmployeeServices(EmployeeServices employeeServices){
        this.employeeRepo = employeeRepo;
    }
    public Employees userLogin(Employees employee) { return employeeRepo.userLogin(employee); }
    public int createEmployee(Employees employee){ return employeeRepo.create(employee); }
    public List<Employees> getAllEmployees(){
        return employeeRepo.getAll();
    }
    public Employees getEmployeeById(int id) { return employeeRepo.getById(id); }
    public Employees updateEmployee(Employees employees) { return employeeRepo.update(employees); }
    public boolean deleteEmployee(Employees employees){ return employeeRepo.delete(employees); }
}
