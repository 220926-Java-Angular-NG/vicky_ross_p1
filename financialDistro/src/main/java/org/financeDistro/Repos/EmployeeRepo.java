package org.financeDistro.Repos;

import org.financeDistro.Models.Employees;
import org.financeDistro.utils.ConnectionManager;
import org.financeDistro.utils.CRUDDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo implements CRUDDaoInterface<Employees> {
    Connection conn;

    public EmployeeRepo(){
        try{
            conn = ConnectionManager.getConnection();
            System.out.println(conn.getSchema());
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public Employees userLogin(Employees employees) {
        try{
            String sql = "SELECT * FROM employeeRegistry WHERE user_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, employees.getUserName());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next() && rs.getString("user_name").equals(employees.getUserName()) && rs.getString("pass_word").equals(employees.getPassword())){
                    return new Employees(rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("user_name"),
                    rs.getString("pass_word"));
            }
        }catch(Exception e){
            System.out.println("This is the userDAO: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int create(Employees employees) {
        try{
            String sql = "INSERT INTO employeeRegistry (employee_id, first_name, last_name, email, user_name, pass_word) VALUES (default, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, employees.getFirstName());
            pstmt.setString(2, employees.getLastName());
            pstmt.setString(3, employees.getEmail());
            pstmt.setString(4, employees.getUserName());
            pstmt.setString(5, employees.getPassword());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.next();

            return rs.getInt("employee_id");
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Employees> getAll() {
        List<Employees> employees = new ArrayList<>();

        try{
            String sql = "SELECT * FROM employeeRegistry";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Employees employee = new Employees();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setUserName(rs.getString("user_name"));

                employees.add(employee);
            }

            return employees;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Employees getById(int id) {
        try{
            String sql = "SELECT * FROM employeeRegistry WHERE employee_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Employees update(Employees employees) {
        try{
            String sql = "UPDATE employeeRegistry SET email = ? WHERE employee_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, employees.getEmail());
            pstmt.setInt(2, employees.getEmployeeId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Employees employees) {
        try{
            String sql = "DELETE FROM employeeRegistry WHERE employee_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employees.getEmployeeId());

            return pstmt.execute();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return true;
    }
}
