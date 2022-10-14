package org.financeDistro.Repos;

import org.financeDistro.Models.Employees;
import org.financeDistro.Models.Reimbursement;
import org.financeDistro.utils.CRUDDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepo implements CRUDDaoInterface<Reimbursement> {
    Connection conn;

    @Override
    public int create(Reimbursement reimbursement) {
        try{
            String sql = "INSERT INTO () VALUES (default, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, reimbursement.getRequestId());
            pstmt.setInt(2, reimbursement.getManagerId());
            pstmt.setInt(3, reimbursement.getEmployeeId());
            pstmt.setInt(4, reimbursement.getDate());
            pstmt.setInt(5, reimbursement.getAmount());
            pstmt.setString(6, reimbursement.getStatus());
            pstmt.setString(7, reimbursement.getDescription());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.next();

            return rs.getInt("request_id");
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Reimbursement> getAll() {
        List<Reimbursement> reimburse = new ArrayList<>();

        try{
            String sql = "SELECT * from reimbursementRequest";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setRequestId(rs.getInt("request_id"));
                //reimbursement.setManagerId(rs.getInt("finance_manager_id"));
                reimbursement.setEmployeeId(rs.getInt("employee_id"));
                reimbursement.setDate(rs.getInt("request_date"));
                reimbursement.setAmount(rs.getInt("request_amount"));
                reimbursement.setStatus(rs.getString("request_status"));
                reimbursement.setDescription(rs.getString("request_descrip"));

                reimburse.add(reimbursement);
            }

            return reimburse;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Reimbursement getById(int id) {
        try {
            String sql = "SELECT * FROM reimbursementRequest WHERE request_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    public List<Reimbursement> getByStatus() {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reimbursementRequest WHERE status = 'pending'";
            Reimbursement reimbursement;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                reimbursement = new Reimbursement();
                reimbursement.setRequestId(rs.getInt("request_id"));
                reimbursement.setEmployeeId(rs.getInt("employee_id"));
                reimbursement.setDate(rs.getInt("request_date"));
                reimbursement.setAmount(rs.getInt("amount"));
                reimbursement.setStatus(rs.getString("request_status"));
                reimbursement.setDescription(rs.getString("request_descrip"));

                reimbursements.add(reimbursement);
            }
            return reimbursements;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Reimbursement update(Reimbursement reimbursement) {
        try{
            String sql = "UPDATE reimbursementRequest SET request_status = ? WHERE request_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, reimbursement.getRequestId());
            pstmt.setString(2, reimbursement.getStatus());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Reimbursement reimbursement) {
        try{
            String sql = "DELETE FROM reimbursementRequest WHERE request_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reimbursement.getRequestId());

            return pstmt.execute();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }
}
