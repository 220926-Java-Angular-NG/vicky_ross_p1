package org.financeDistro.Repos;

import org.financeDistro.Models.Employees;
import org.financeDistro.Models.Managers;
import org.financeDistro.utils.ConnectionManager;
import org.financeDistro.utils.CRUDDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerRepo implements CRUDDaoInterface<Managers> {
    Connection conn;

    public Managers managerLogin(Managers managers) {
        try{
            String sql = "SELECT * FROM employeeRegistry WHERE user_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, managers.getUserName());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next() && rs.getString("user_name").equals(managers.getUserName()) && rs.getString("pass_word").equals(managers.getPassword())){
                return new Managers(rs.getInt("financial_manager_id"),
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
    public int create(Managers managers) {
        try{
            String sql = "INSERT INTO managerRegistry (finance_manager_id, first_name, last_name, email, user_name, pass_word) VALUES (default, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, managers.getFirstName());
            pstmt.setString(2, managers.getLastName());
            pstmt.setString(3, managers.getEmail());
            pstmt.setString(4, managers.getUserName());
            pstmt.setString(5, managers.getPassword());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.next();

            return rs.getInt("finance_manager_id");
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Managers> getAll() {
        List<Managers> managers = new ArrayList<>();

        try{
            String sql = "SELECT * from managerRegistry";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Managers manager = new Managers();
                manager.setManagerId(rs.getInt("finance_manager_id"));
                manager.setFirstName(rs.getString("first_name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setEmail(rs.getString("email"));
                manager.setUserName(rs.getString("user_name"));

                managers.add(manager);
            }
            return managers;
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Managers getById(int id) {
        try{
            String sql = "SELECT * FROM managerRegistry WHERE finance_manager_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Managers update(Managers managers) {
        try{
            String sql = "UPDATE managerRegistry SET email = ? WHERE finance_manager_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, managers.getEmail());
            pstmt.setInt(2, managers.getManagerId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Managers managers) {
        try{
            String sql = "DELETE FROM managerRegistry WHERE finance_manager_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, managers.getManagerId());

            return pstmt.execute();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return true;
    }
}
