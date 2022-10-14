package org.financeDistro.Models;

public class Reimbursement {
    private int requestId;
    private int employeeId;
    private int managerId;
    private int date;
    private int amount;
    private String status;

    private String description;

    public Reimbursement(){

    }

    public Reimbursement(int requestId, int employeeId, int managerId, int date, int amount, String status, String description){
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.description = description;
    }
    public Reimbursement(int employeeId, String status){
        this.employeeId = employeeId;
        this.status = status;
    }

    public Reimbursement(int employeeId){
        this.employeeId = employeeId;
    }

    public int getRequestId() { return requestId; }
    public void setRequestId(int request_id){
        this.requestId = requestId;
    }
    public int getEmployeeId(){
        return employeeId;
    }
    public void setEmployeeId(int employee_id){
        this.employeeId = employeeId;
    }
    public int getManagerId(){
        return managerId;
    }
    public void setManagerId(){
        this.managerId = managerId;
    }
    public int getDate(){
        return date;
    }
    public void setDate(int request_date){
        this.date = date;
    }
    public int getAmount() {return amount; }
    public void setAmount(int request_amount) {this.amount = amount; }
    public String getStatus(){
        return status;
    }
    public void setStatus(String request_status){ this.status = status; }
    public String getDescription(){return description;}
    public void setDescription(String request_descrip){this.description = description;}

    @Override
    public String toString(){
        return "Reimbursement Ticket{" +
                "request_id = " + requestId +
                ", employee_id = " + employeeId +
                ", manager_id = " + managerId +
                ", date = " + date +
                ", amount = " + amount +
                ", status = " + status +
                ", description = " + description +
                '}';
    }
}
