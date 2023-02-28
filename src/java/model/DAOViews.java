/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOViews extends DBContext {

    public static void main(String[] args) {
        DAOViews dao = new DAOViews();
        System.out.println(dao.getLoginCountForCurrentDay());

    }

    public int getTotalView() {
        int number = 0;

        String sql = "SELECT SUM(View) AS Total FROM Views";
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                number = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public Vector<Integer> getAccessByMonth(int year) {
        Vector<Integer> vector = new Vector<>();

        String sql = "SELECT MONTH(DateView),SUM(Access) AS Total FROM Views WHERE YEAR(DateView) = ? GROUP BY MONTH(DateView) ORDER BY MONTH(DateView)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1,year);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                vector.add(rs.getInt("Total"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int getLoginCountForCurrentDay() {
        int loginCount = 0;
        try {
            // Get current date
            Date currentDate = new Date(System.currentTimeMillis());

            // Query database for entry for current date
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT Access FROM Views WHERE dateView = ?");
            stmt.setDate(1, currentDate);
            ResultSet rs = stmt.executeQuery();

            // If entry exists, get login count
            if (rs.next()) {
                loginCount = rs.getInt("Access");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginCount;
    }

    public void saveLoginCountForCurrentDay(int loginCount) {
        try {
            // Get current date
            Date currentDate = new Date(System.currentTimeMillis());

            // Query database for entry for current date
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM Views WHERE dateView = ?");
            stmt.setDate(1, currentDate);
            ResultSet rs = stmt.executeQuery();

            // If entry exists, update login count
            if (rs.next()) {
                stmt = connection.prepareStatement(
                        "UPDATE Views SET Access = ? WHERE dateView = ?");
                stmt.setInt(1, loginCount);
                stmt.setDate(2, currentDate);
                stmt.executeUpdate();
            } else {
                // Otherwise, create new entry
                stmt = connection.prepareStatement(
                        "INSERT INTO Views (dateView, Access) VALUES (?, ?)");
                stmt.setDate(1, currentDate);
                stmt.setInt(2, loginCount);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}