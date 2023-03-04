/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Categories;
import entity.Customers;
import entity.Products;
import entity.Suppliers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daova
 */
public class DAOSuppliers extends DBContext {

    public static void main(String[] args) {
        DAOSuppliers daoSup = new DAOSuppliers();
        System.out.println(daoSup.GetSupplierByEmail("apple@gmail.com"));
    }
    
    /**
     * It returns a vector of suppliers, each of which has a number of products
     * 
     * @return A vector of suppliers.
     */
    public Vector<Suppliers> getNumberProductsBySupplier() {
        String sql = "SELECT Products.SupplierID, COUNT(ProductID) AS Number FROM dbo.Suppliers INNER JOIN dbo.Products ON Products.SupplierID = Suppliers.SupplierID\n"
                + "GROUP BY Products.SupplierID";
        Vector<Suppliers> vector = new Vector<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                int sid = rs.getInt("SupplierID");
                int number = rs.getInt("Number");
                Suppliers s = getSuppliersBySupplierID(sid);
                s.setNumber(number);
                vector.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It returns a vector of categories, each category has a number of products that belong to it
     * 
     * @param id SupplierID
     * @return A vector of Categories objects.
     */
    public Vector<Categories> NumberOfProductsBySupplier(int id) {
        DAOCategories daoCategories = new DAOCategories();
        Vector<Categories> vector = new Vector<>();
        String sql = "SELECT CategoryID,COUNT(*) AS Number FROM dbo.Products WHERE SupplierID = ? GROUP BY CategoryID";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("CategoryID");
                int number = rs.getInt("Number");
                Categories category = daoCategories.getCategoryByCategoryID(cid);
                category.setNumber(number);
                vector.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It takes a vector of suppliers, and returns a vector of suppliers
     * 
     * @param vector the vector of data
     * @param start the index of the first element in the vector to be displayed
     * @param end the end index of the vector
     * @return A vector of suppliers
     */
    public Vector<Suppliers> getListByPage(Vector<Suppliers> vector,
            int start, int end) {
        Vector<Suppliers> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(vector.get(i));
        }
        return arr;
    }

    /**
     * It gets the next 5 suppliers from the database, starting from the amount of suppliers already
     * displayed
     * 
     * @param amount the amount of rows to skip
     * @return A vector of Suppliers objects.
     */
    public Vector<Suppliers> getNextSuppliersByAdmin(int amount) {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM Suppliers ORDER BY SupplierID ASC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, amount);
            ResultSet rs = pre.executeQuery();
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

    /**
     * It gets all the suppliers from the database and returns them as a vector
     * 
     * @return A vector of Suppliers objects.
     */
    public Vector<Suppliers> getAllSuppliersByAdmin() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM Suppliers ORDER BY SupplierID ASC";
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

    /**
     * UpdateSupplier() is a function that updates the information of a supplier in the database
     * 
     * @param s Suppliers
     * @return The number of rows affected by the update.
     */
    public int UpdateSupplier(Suppliers s) {
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

    /**
     * The function takes a Supplier object as a parameter and inserts the object's data into the
     * database
     * 
     * @param s Suppliers
     * @return The number of rows affected by the SQL statement.
     */
    public int AddNewSupplier(Suppliers s) {
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

    /**
     * It updates the Suppliers table in the database with the values of the Suppliers object passed to
     * it
     * 
     * @param s Suppliers
     * @return The number of rows affected by the update.
     */
    public int AccountSupplier(Suppliers s) {
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

    /**
     * It takes a string as a parameter and returns a Suppliers object
     * 
     * @param name String
     * @return A Supplier object.
     */
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
    
    /**
     * It takes a string as a parameter and returns a Suppliers object
     * 
     * @param name String
     * @return A Supplier object.
     */
    public Suppliers GetSupplierByHomePage(String name) {
        Suppliers sup = null;
        String sql = "SELECT * FROM dbo.Suppliers WHERE Homepage = ?";

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
    
    /**
     * It takes a string as a parameter and returns a Suppliers object
     * 
     * @param name String
     * @return A Supplier object.
     */
    public Suppliers GetSupplierByEmail(String name) {
        Suppliers sup = null;
        String sql = "SELECT * FROM dbo.Suppliers WHERE Email = ?";

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
    
    /**
     * It takes a string as a parameter and returns a Suppliers object
     * 
     * @param name String
     * @return A Supplier object.
     */
    public Suppliers GetSupplierByPhone(String name) {
        Suppliers sup = null;
        String sql = "SELECT * FROM dbo.Suppliers WHERE Phone = ?";

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

    /**
     * Get the number of products of each supplier that are shipped by a specific shipper
     * 
     * @param shipperID 1
     * @return A vector of Suppliers objects.
     */
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

    /**
     * It returns the number of suppliers that have products that have been shipped by a specific
     * shipper
     * 
     * @param shipperID the ID of the shipper
     * @return The number of suppliers that have products that have been shipped by a particular
     * shipper.
     */
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

    /**
     * It returns a vector of suppliers
     * 
     * @return A vector of Suppliers objects.
     */
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

    /**
     * It returns the number of suppliers in the database
     * 
     * @return The number of suppliers in the database.
     */
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

    /**
     * It returns a vector of suppliers that have products that are laptops
     * 
     * @return A vector of suppliers.
     */
    public Vector<Suppliers> getAllSuppliersLaptop() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT DISTINCT Suppliers.SupplierID, Suppliers.CompanyName FROM dbo.Categories INNER JOIN dbo.Products \n"
                + "ON Products.CategoryID = Categories.CategoryID INNER JOIN dbo.Suppliers \n"
                + "ON Suppliers.SupplierID = Products.SupplierID\n"
                + "WHERE CategoryName LIKE '%LAPTOP%' AND Status = 1";
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

    /**
     * It returns a vector of suppliers that have products in the Tablet category
     * 
     * @return A vector of Suppliers.
     */
    public Vector<Suppliers> getAllSuppliersTablet() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT DISTINCT Suppliers.SupplierID, Suppliers.CompanyName FROM dbo.Categories INNER JOIN dbo.Products \n"
                + "ON Products.CategoryID = Categories.CategoryID INNER JOIN dbo.Suppliers \n"
                + "ON Suppliers.SupplierID = Products.SupplierID\n"
                + "WHERE CategoryName LIKE '%Tablet%' AND Status = 1";
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

    /**
     * It returns a vector of suppliers that supply products in the SmartPhone category
     * 
     * @return A vector of suppliers.
     */
    public Vector<Suppliers> getAllSuppliersSmartPhone() {
        Vector<Suppliers> vector = new Vector<>();
        String sql = "SELECT DISTINCT Suppliers.SupplierID, Suppliers.CompanyName FROM dbo.Categories INNER JOIN dbo.Products \n"
                + "ON Products.CategoryID = Categories.CategoryID INNER JOIN dbo.Suppliers \n"
                + "ON Suppliers.SupplierID = Products.SupplierID\n"
                + "WHERE CategoryName LIKE '%SmartPhone%' AND Status = 1";
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

    /**
     * It returns a Supplier object based on the ProductID
     * 
     * @param pid product id
     * @return A Supplier object.
     */
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
                sup = new Suppliers(supplierID, companyName, phone, email, homePage, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sup;
    }

    /**
     * It takes an integer as a parameter and returns a Suppliers object
     * 
     * @param sID SupplierID
     * @return A Supplier object.
     */
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
                supplier = new Suppliers(supplierID, companyName, phone, email, homePage, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return supplier;
    }

    

}
