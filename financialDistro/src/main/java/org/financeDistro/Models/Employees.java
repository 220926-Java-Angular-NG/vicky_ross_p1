package org.financeDistro.Models;

public class Employees {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    public Employees(){
    }

    //Information being retrieved from the DB
    public Employees(int employeeId, String firstName, String lastName, String email, String userName, String password){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    //Information being created or sent to the DB
    public Employees(String firstName, String lastName, String email, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
public Employees(String userName, String password){
        this.userName = userName;
        this.password = password;
}
    public int getEmployeeId(){
        return employeeId;
    }
    public void setEmployeeId(int employeeId){
        this.employeeId = employeeId;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = this.email;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password = password;
    }

    @Override
    public String toString(){
        return "Employee{" +
                "employee_id = " + employeeId +
                ", first_name = " + firstName +
                ", last_name = " + lastName +
                ", email = " + email +
                ", username = " + userName +
                ", password = " + password +
                '}';
    }
}
