package org.financeDistro.Models;

public class Managers {
    private int managerId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    public Managers(){

    }

    //Information retrieved from the DB
    public Managers(int managerId, String firstName, String lastName, String email, String userName, String password){
        this.managerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    //Information created or sent to the DB
    //Does not include automatically serialized ID in our DB
    public Managers(String firstName, String lastName, String email, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
    //login
    public Managers(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getManagerId(){
        return managerId;
    }
    public void setManagerId(int managerId){
        this.managerId = managerId;
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
                "manager_id = " + managerId +
                ", first_name = " + firstName +
                ", last_name = " + lastName +
                ", email = " + email +
                ", username = " + userName +
                ", password = " + password +
                '}';
    }
}
