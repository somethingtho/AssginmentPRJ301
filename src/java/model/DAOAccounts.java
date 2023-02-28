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

    
    public double rateAccountActive() {
        double rate = 0;
        int total = TotalAccounts();
        int active = AccountActive();
        if (total != 0) {
            rate = (double) total / active;
        }
        return rate;
    }

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

    public double rateNewAccount() {
        double rate = 0;

        int total = TotalAccounts();
        if (total != 0) {
            rate = (double) newUserInMonth() / total;
        }
        return rate;
    }

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

    //List all Accounts
    public Vector getAllAccounts() {
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
