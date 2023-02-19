/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Customers;
import entity.Suppliers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daova
 */
public class DAOSuppliers extends DBContext {
    
    public int UpdateSupplier(Suppliers s){
        int number = 0;
        String sql = "UPDATE Suppliers SET CompanyName = ?, Phone = ?, HomePage = ?, Email =?, Status = ? WHERE SupplierID = ?";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getHomePage());
            pre.setString(4, s.getEmail());
            pre.setBoolean(5, s.isStatus());
            pre.setInt(6, s.getSupplierID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return number;
    }
    
    
    public int AddNewSupplier(Suppliers s){
        int number = 0;
        String sql = "INSERT INTO Suppliers(CompanyName, Phone, Email, homePage, Status) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setString(4, s.getHomePage());
            pre.setBoolean(5, s.isStatus());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
    }
    
    public int AccountSupplier(Suppliers s ){
        int number = 0;
        String sql = "UPDATE Suppliers SET Status = ?, CompanyName = ?, Email = ?, Phone = ?, HomePage = ? WHERE SupplierID = ?";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, s.isStatus());
            pre.setString(2, s.getCompanyName());
            pre.setString(3, s.getEmail());
            pre.setString(4, s.getPhone());
            pre.setString(5, s.getHomePage());
            pre.setInt(6, s.getSupplierID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
    }


    public Suppliers GetSupplierByCompanyName(String name) {
        Suppliers sup = null;
        String sql = "SELECT * FROM dbo.Suppliers WHERE CompanyName = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String homePage = rs.getString("HomePage");
                boolean status = rs.getBoolean("Status");
                sup = new Suppliers(supplierID, companyName, phone, email, homePage, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sup;
    }


    public Vector<Suppliers> GetNumberProductsByShipper(int shipperID) {
        Vector<Suppliers> vector = new Vector<>();

        String sql = "SELECT a.SupplierID, COUNT(a.SupplierID) as NumberProducts FROM (SELECT ProductID,Products.SupplierID FROM dbo.Products INNER JOIN dbo.Suppliers \n"
                + "ON Suppliers.SupplierID = Products.SupplierID) AS a INNER JOIN\n"
                + "(SELECT ProductID FROM dbo.Orders INNER JOIN dbo.OrderDetails \n"
                + "ON OrderDetails.OrderID = Orders.OrderID\n"
                + "WHERE ShipVia = ?) AS B ON B.ProductID = a.ProductID\n"
                + "GROUP BY a.SupplierID";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt("SupplierID");
                int number = rs.getInt("NumberProducts");
                vector.add(new Suppliers(number, getSuppliersBySupplierID(sid)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int TotalSuppliersByShipper(int shipperID) {
        int number = 0;
        String sql = "SELECT COUNT(DISTINCT(SupplierID)) FROM dbo.Products WHERE ProductID IN(SELECT ProductID FROM dbo.OrderDetails WHERE OrderID IN (SELECT OrderID FROM dbo.Orders WHERE ShipVia =?))";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    //get all supplier
    public Vector<Suppliers> getAllSuppliers() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT * FROM Suppliers";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String supplierName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String homePage = rs.getString("HomePage");
                String email = rs.getString("Email");
                boolean status = rs.getBoolean("Status");
                vector.add(new Suppliers(supplierID, supplierName, phone, email, homePage, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int TotalSuppliers() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Suppliers";
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

    //SELECT Suppliers cung by laptop
    public Vector<Suppliers> getAllSuppliersLaptop() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT DISTINCT Suppliers.SupplierID, Suppliers.CompanyName FROM dbo.Categories INNER JOIN dbo.Products \n"
                + "ON Products.CategoryID = Categories.CategoryID INNER JOIN dbo.Suppliers \n"
                + "ON Suppliers.SupplierID = Products.SupplierID\n"
                + "WHERE CategoryName LIKE '%LAPTOP%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String supplierName = rs.getString("CompanyName");
                vector.add(new Suppliers(supplierID, supplierName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    //get supplier by tablet
    public Vector<Suppliers> getAllSuppliersTablet() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT DISTINCT Suppliers.SupplierID, Suppliers.CompanyName FROM dbo.Categories INNER JOIN dbo.Products \n"
                + "ON Products.CategoryID = Categories.CategoryID INNER JOIN dbo.Suppliers \n"
                + "ON Suppliers.SupplierID = Products.SupplierID\n"
                + "WHERE CategoryName LIKE '%Tablet%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String supplierName = rs.getString("CompanyName");
                vector.add(new Suppliers(supplierID, supplierName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    // Get all supplier smartphone
    public Vector<Suppliers> getAllSuppliersSmartPhone() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT DISTINCT Suppliers.SupplierID, Suppliers.CompanyName FROM dbo.Categories INNER JOIN dbo.Products \n"
                + "ON Products.CategoryID = Categories.CategoryID INNER JOIN dbo.Suppliers \n"
                + "ON Suppliers.SupplierID = Products.SupplierID\n"
                + "WHERE CategoryName LIKE '%SmartPhone%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
               int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                vector.add(new Suppliers(supplierID, companyName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    //Get supplier by productID
    public Suppliers getSupplierByProductID(int pid) {
        Suppliers sup = null;
        String sql = "SELECT * FROM dbo.Suppliers WHERE SupplierID =  (SELECT SupplierID FROM dbo.Products WHERE ProductID = ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String homePage = rs.getString("HomePage");
                boolean status = rs.getBoolean("Status");
                sup = new Suppliers(supplierID, companyName, phone, email, homePage,status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sup;
    }

    //get supplier by supplierID
    public Suppliers getSuppliersBySupplierID(int sID) {
        Suppliers supplier = null;
        String sql = "SELECT * FROM Suppliers WHERE SupplierID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String homePage = rs.getString("HomePage");
                boolean status = rs.getBoolean("Status");
                supplier = new Suppliers(supplierID, companyName, phone, email, homePage,status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return supplier;
    }


    public static void main(String[] args) {
        DAOSuppliers daoSup = new DAOSuppliers();
        System.out.println(daoSup.TotalSuppliersByShipper(1));
        System.out.println(daoSup.TotalSuppliers());
        Vector<Suppliers> vector = daoSup.GetNumberProductsByShipper(1);
        for (Suppliers suppliers : vector) {
            System.out.println(suppliers);
        }
    }

}
