/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daova
 */
public class DAOAccounts extends DBContext {

    
    /**
     * This function calculates the rate of active accounts
     * 
     * @return The rate of active accounts.
     */
    public double rateAccountActive() {
        double rate = 0;
        int total = TotalAccounts();
        int active = AccountActive();
        if (total != 0) {
            rate = (double) total / active;
        }
        return rate;
    }

    /**
     * It returns the number of active accounts in the database.
     * </code>
     * 
     * @return The number of active accounts.
     */
    public int AccountActive() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.Accounts WHERE Status =1 ";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return number;
    }

    /**
     * It returns the total number of accounts in the database
     * 
     * @return The total number of accounts in the database.
     */
    public int TotalAccounts() {
        String sql = "SELECT COUNT(*) AS Number FROM dbo.Accounts";
        int total = 0;
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                total = rs.getInt("Number");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    /**
     * This function calculates the rate of new accounts created in the last month
     * 
     * @return The rate of new accounts created in the month.
     */
    public double rateNewAccount() {
        double rate = 0;

        int total = TotalAccounts();
        if (total != 0) {
            rate = (double) newUserInMonth() / total;
        }
        return rate;
    }

    /**
     * It returns the number of new users in the current month.
     * </code>
     * 
     * @return The number of new users in the current month.
     */
    public int newUserInMonth() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.Accounts WHERE MONTH(RegistrationDate) = MONTH(GETDATE()) ";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return number;
    }

    /**
     * It gets all the accounts from the database and returns them as a vector
     * 
     * @return A vector of accounts.
     */
    public Vector<Accounts> getAllAccounts() {
        Vector<Accounts> vector = new Vector<>();
        String sql = "SELECT * FROM Accounts";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("UserName");
                int role = rs.getInt("Role");
                vector.add(new Accounts(id, userName, role));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It gets the username from the database and returns it as an object
     * 
     * @param username the username of the account
     * @return The method is returning an object of type Accounts.
     */
    public Accounts getUsername(String username) {
        Accounts acc = null;
        String sql = "SELECT * FROM Accounts WHERE Username = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("UserName");
                int role = rs.getInt("Role");
                acc = new Accounts(id, userName, role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return acc;
    }

    /**
     * It gets the account information from the database and returns it as an object
     * 
     * @param aid the ID of the account
     * @return The method is returning an object of type Accounts.
     */
    public Accounts getID(int aid) {
        Accounts acc = null;
        String sql = "SELECT * FROM Accounts WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, aid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("UserName");
                int role = rs.getInt("Role");
                String dateRegis = rs.getString("RegistrationDate");
                boolean status = rs.getBoolean("Status");
                acc = new Accounts(id, userName, role, userName, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return acc;
    }

}
