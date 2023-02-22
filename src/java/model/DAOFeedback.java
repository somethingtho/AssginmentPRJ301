/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customers;
import entity.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author daova
 */
public class DAOFeedback extends DBContext{
    
    public int UpdateFeedback(Feedback feedback){
        int number = 0;
        String sql = "UPDATE Feedback SET ContentRep = ?, Status = 1 WHERE ID = ?";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1,feedback.getContentRep());
            pre.setInt(2, feedback.getId());
            
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
    }
    
    
    public int TotalFeedbacks(){
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Feedback";
        ResultSet rs= getData(sql);
        try {
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    
    //list all feedback
    public Vector getAllFeedBack(){
        Vector<Feedback> vector = new Vector<>();
        String sql = "SELECT * FROM Feedback";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                String contentSend = rs.getString("ContentSend");
                String contentRep = rs.getString("ContentRep");
                boolean status = rs.getBoolean("Status");
                vector.add(new Feedback(id, idAccount, contentSend, contentRep, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return vector;
    }
    
    //Add Feedback
    public int AddFeedback(int idAccount, String email, String contendSend, int role) {
        int number = 0;
        String sql = "INSERT INTO dbo.Feedback(IDAccount, Email,ContentSend, Role,Status) VALUES(?, ?, ?, ?, 0)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, idAccount);
            pre.setString(2, email);
            pre.setString(3, contendSend);
            pre.setInt(4, role);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public int AddFeedback(String email, String contendSend, int role) {
        int number = 0;
        String sql = "INSERT INTO dbo.Feedback(Email,ContentSend, Role,Status) VALUES(?, ?, ?, 0)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, contendSend);
            pre.setInt(3, role);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    //Delete  Feedback
    public int DeleteFeedback(int id) {
        int number = 0;
        String sql = "DELETE FROM Feedback WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    //Update feedback
    public int UpdateShippers(Feedback feedback) {
        int number = 0;
        String sql = "UPDATE Feedback SET IDAccount = ?, ContentSend = ?, ContentRep = ?, Status WHERE ID = ?";
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, feedback.getIdAccount());
            pre.setString(2, feedback.getContentSend());
            pre.setString(2, feedback.getContentRep());
            pre.setBoolean(4, feedback.isStatus());
            pre.setInt(5, feedback.getId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public static void main(String[] args) {
        DAOFeedback dao = new DAOFeedback();
        DAOCustomers daoCus = new DAOCustomers();
        Customers cus = daoCus.getCustomerByEmail("daoson03112002@gmail.com");
        System.out.println(dao.AddFeedback(22, "daoson03112002@gmail.com", "hello", 3));
        
    }
    
}
