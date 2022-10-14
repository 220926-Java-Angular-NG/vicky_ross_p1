package org.financeDistro.Controllers;

import org.financeDistro.Models.CurrentUser;
import org.financeDistro.Models.Employees;
import org.financeDistro.Services.EmployeeServices;
import io.javalin.http.Handler;

public class EmployeeController {
    EmployeeServices emService;

    public EmployeeController() {
        emService = new EmployeeServices();
    }

    public Handler userLogin = context -> {
        Employees employee = context.bodyAsClass(Employees.class);
        CurrentUser.currentEmp = emService.userLogin(employee);
        System.out.println(CurrentUser.currentEmp.toString());
        if(CurrentUser.currentEmp != null){
            context.json(employee).result("Logged in.").status(202);
        }else{
            context.result("Unable to find user.").status(400);
        }
    };

    public Handler createNewEmployee = context -> {
        Employees employee = context.bodyAsClass(Employees.class);
        int employeeId = emService.createEmployee(employee);
        System.out.println(employee.toString());

        if(employeeId > 0){
            employee.setEmployeeId(employeeId);
            context.json(employee).result("Welcome, User.").status(200);
        }else{
            context.result("Unable to create user.").status(400);
        }
    };

    public Handler getAllEmployees = context -> {
        context.json(emService.getAllEmployees());
    };

    public Handler getEmployeeById = context -> {
        String param = context.pathParam("employee_id");
        try{
            int employeeId = Integer.parseInt(param);
            Employees employee = emService.getEmployeeById(employeeId);
            if(employee != null){
                context.json(employee).result("Employee obtained.").status(202);
            }else{
                context.result("Unable to find user.").status(400);
            }
        }catch(NumberFormatException nFException){
            System.out.println(nFException.getMessage());
        }
    };

    public Handler updateEmployee = context -> {
        Employees employee = context.bodyAsClass(Employees.class);
        employee = emService.updateEmployee(employee);
        if(employee != null){
            context.json(employee).result("Successful user update.").status(200);
        }else{
            context.result("Unable to update user.").status(400);
        }
    };

    public Handler deleteEmployee = context -> {
        Employees employee = context.bodyAsClass(Employees.class);
        if(employee != null){
            context.status(200).json(emService.deleteEmployee(employee)).result("Successfully deleted.");
        }else{
            context.status(400).result("Unable to delete user.");
        }
    };
}
