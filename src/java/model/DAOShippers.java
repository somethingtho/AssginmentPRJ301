/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Shippers;
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
public class DAOShippers extends DBContext {

    public int UpdateShipper(Shippers s) {
        int number = 0;

        String sql = "UPDATE Shippers SET CompanyName = ?, Phone= ?, Email= ?, Status=? Where shipperID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setBoolean(4, s.isStatus());
            pre.setInt(5, s.getShipperID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
        }

        return number;
    }

    public int AddNewShipper(Shippers s) {
        int number = 0;
        String sql = "INSERT INTO Shippers(CompanyName, Phone, Email, Status) VALUES(?,?,?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setBoolean(4, s.isStatus());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
        }

        return number;
    }

    public int AccountShipper(Shippers s) {
        int number = 0;

        String sql = "UPDATE Shippers SET CompanyName = ?, Phone = ?, Email = ?, Status = ? WHERE ShipperID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setBoolean(4, s.isStatus());
            pre.setInt(5, s.getShipperID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    public Shippers getShipperByCompanyName(String s) {
        Shippers x = null;
        String sql = "SELECT * FROM Shippers WHERE CompanyName = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ShipperID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                x = new Shippers(id, companyName, phone, email, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return x;
    }

    public int TotalShipperBySupplier(int supplierID) {
        int number = 0;
        String sql = "SELECT COUNT(Orders.ShipVia) FROM dbo.Orders WHERE Orders.OrderID IN(\n"
                + "SELECT DISTINCT(OrderDetails.OrderID) FROM dbo.OrderDetails \n"
                + "WHERE OrderDetails.ProductID IN (SELECT Products.ProductID FROM dbo.Products WHERE SupplierID = ?)\n"
                + ")";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int TotalShippers() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Shippers";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Vector getAllShippersByAdmin() {
        String sql = "SELECT * FROM Shippers";
        Vector<Shippers> vector = new Vector<>();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt("ShipperID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                vector.add(new Shippers(id, companyName, phone, email, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    //List All Shippers
    public Vector getAllShippers() {
        String sql = "SELECT * FROM Shippers WHERE Status = 1";
        Vector<Shippers> vector = new Vector<>();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt("ShipperID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                vector.add(new Shippers(id, companyName, phone, email, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Shippers getShipperByShipperID(int sID) {
        Shippers shipper = null;
        String sql = "SELECT * FROM Shippers WHERE ShipperID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int shipperId = rs.getInt("ShipperID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                boolean status = rs.getBoolean("Status");
                shipper = new Shippers(shipperId, companyName, phone, email, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return shipper;
    }

    public static void main(String[] args) {
        DAOShippers dao = new DAOShippers();
        Vector<Shippers> vector = dao.getAllShippers();
        System.out.println(dao.getShipperByShipperID(1));
    }
}
