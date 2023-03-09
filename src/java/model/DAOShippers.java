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

    
    
    /**
     * It returns a Shippers object with the given Phone 
     * 
     * @param s String
     * @return A Shippers object.
     */
    public Shippers getShipperByPhone(String s) {
        Shippers x = null;
        String sql = "SELECT * FROM Shippers WHERE Phone = ?";
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
    
    /**
     * It returns a Shippers object with the given Email
     * 
     * @param s String
     * @return A Shippers object.
     */
    public Shippers getShipperByEmail(String s) {
        Shippers x = null;
        String sql = "SELECT * FROM Shippers WHERE Email = ?";
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
    

    /**
     * UpdateShipper(Shippers s) is a function that updates the Shippers table in the database with the
     * values of the Shippers object s
     * 
     * @param s is the object of the Shippers class
     * @return The number of rows affected by the update.
     */
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

    /**
     * This function is used to add a new shipper to the database
     * 
     * @param s is the object of the Shippers class
     * @return The number of rows affected by the SQL statement.
     */
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

    /**
     * This function is used to update the information of the shipper
     * 
     * @param s Shippers
     * @return The number of rows affected by the update.
     */
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

    /**
     * It returns a Shippers object with the given company name
     * 
     * @param s String
     * @return A Shippers object.
     */
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

    /**
     * It returns the number of shippers that have shipped products from a given supplier
     * 
     * @param supplierID the supplier ID
     * @return The number of shippers used by a supplier.
     */
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

    /**
     * It returns the number of rows in the Shippers table
     * 
     * @return The number of shippers in the database.
     */
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

    /**
     * It returns a vector of Shippers objects, which are created from the data in the Shippers table
     * 
     * @return A vector of Shippers objects.
     */
    public Vector<Shippers> getAllShippersByAdmin() {
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
    
    /**
     * It returns a vector of Shippers objects, which are created from the data in the Shippers table
     * in the database
     * 
     * @return A vector of Shippers objects.
     */
    public Vector<Shippers> getAllShippers() {
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

    /**
     * It returns a Shipper object based on the ShipperID passed in as a parameter
     * 
     * @param sID the shipperID
     * @return A Shipper object.
     */
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
        System.out.println(dao.getShipperByEmail("JTExpress@gmail.com").toString());
    }
}
