/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
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
public class DAOCategories extends DBContext {
    
    public int AddNewCategory(Categories cate){
        int number = 0;
        String sql = "INSERT INTO Categories(CategoryName) VALUES(?)";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
    }

    public Vector getAllCategoriesAndNumberProducts() {
        Vector<Categories> vector = new Vector<>();
        String sql = "SELECT Categories.*, a.NumberOfCategory FROM dbo.Categories INNER JOIN \n"
                + "( SELECT Categories.CategoryID, COUNT(ProductID) AS NumberOfCategory FROM dbo.Categories INNER JOIN dbo.Products ON Products.CategoryID = Categories.CategoryID\n"
                + "GROUP BY Categories.CategoryID) AS a ON a.CategoryID = Categories.CategoryID";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                int number = rs.getInt("NumberOfCategory");
                vector.add(new Categories(categoryID, categoryName, number));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int UpdateCategory(Categories category) {
        int number = 0;
        String sql = "UPDATE Categories SET CategoryName = ? WHERE CategoryID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, category.getCategoryName());
            pre.setInt(2, category.getCategoryID());
            number = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;

    }

    public Categories getCategoryByCategoryName(String name) {
        Categories c = null;
        String sql = "SELECT * FROM Categories WHERE CategoryName = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                c = new Categories(categoryID, categoryName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public int ToalCategoriesByShipper(int shipperID) {
        int number = 0;
        String sql = "SELECT COUNT(DISTINCT(CategoryID)) FROM dbo.Products WHERE ProductID IN(SELECT ProductID FROM dbo.OrderDetails WHERE OrderID IN (SELECT OrderID FROM dbo.Orders WHERE ShipVia =?))";

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

    public int ToalCategoriesBySuppliers(int supplierID) {
        int number = 0;
        String sql = "SELECT COUNT(DISTINCT(Products.CategoryID)) FROM dbo.Products WHERE SupplierID = ?";

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

    public int TotalCategories() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Categories";
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

    //list all categories
    public Vector getAllCategories() {
        Vector<Categories> vector = new Vector<>();
        String sql = "SELECT * FROM Categories ORDER BY CategoryID";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                vector.add(new Categories(categoryID, categoryName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    //Add Categories
    public int InsertCategories(String categoryName) {
        int number = 0;
        String sql = "INSERT INTO Categories(CategoryName) VALUES(?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, categoryName);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int DeleteCategories(int id) {
        int number = 0;
        String sql = "DELETE FROM Categories WHERE CategoryID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Categories getCategoryNameByProductID(int pid) {
        Categories cate = null;
        String sql = "SELECT Categories.* FROM dbo.Categories INNER JOIN dbo.Products ON Products.CategoryID = Categories.CategoryID WHERE ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                cate = new Categories(categoryID, categoryName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cate;
    }

    public Categories getCategoryByCategoryID(int cID) {
        Categories category = null;
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                category = new Categories(categoryID, categoryName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return category;
    }

    public static void main(String[] args) {
        DAOCategories daoCate = new DAOCategories();
        Categories cate = daoCate.getCategoryByCategoryID(1);
        cate.setCategoryName("SmartPhone");
    }
}
