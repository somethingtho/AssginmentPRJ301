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

    /**
     * It returns a vector of categories, each category has a number of products
     * 
     * @return A vector of Categories objects.
     */
    public Vector<Categories> NumberProductsByCategories() {
        Vector<Categories> vector = new Vector<>();

        String sql = "SELECT CategoryID,COUNT(CategoryID) AS Number FROM dbo.Products GROUP BY CategoryID ";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cateID = rs.getInt("CategoryID");
                int number = rs.getInt("Number");
                Categories cate = getCategoryByCategoryID(cateID);
                cate.setNumber(number);
                vector.add(cate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

    /**
     * This function is used to add a new category to the database
     * 
     * @param cate is the object of the Categories class
     * @return The number of rows affected by the SQL statement.
     */
    public int AddNewCategory(Categories cate) {
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

    /**
     * It returns a vector of categories, each category has a categoryID, categoryName, and number of
     * products in that category
     * 
     * @return A vector of categories.
     */
    public Vector<Categories> getAllCategoriesAndNumberProducts() {
        Vector<Categories> vector = new Vector<>();
        String sql = "SELECT Categories.CategoryID, CategoryName, COUNT(ProductID) AS NumberOfCategory FROM dbo.Products RIGHT JOIN dbo.Categories ON Categories.CategoryID = Products.CategoryID\n"
                + "GROUP BY Categories.CategoryID, CategoryName";
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

    /**
     * UpdateCategory() is a function that updates the category name of a category in the database
     * 
     * @param category is the object of the Categories class
     * @return The number of rows affected by the update.
     */
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

    /**
     * It takes a string as a parameter, and returns a Categories object
     * 
     * @param name String
     * @return A category object.
     */
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

    /**
     * It returns the number of distinct categories of products that have been shipped by a given
     * shipper
     * 
     * @param shipperID the ID of the shipper
     * @return The number of categories that have been shipped by a particular shipper.
     */
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

    /**
     * It returns the number of distinct categories that a supplier has
     * 
     * @param supplierID the supplier ID
     * @return The number of categories that a supplier has.
     */
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

    /**
     * It returns the number of rows in the Categories table
     * 
     * @return The number of categories in the database.
     */
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

    /**
     * It gets all the categories from the database and returns them in a vector
     * 
     * @return A vector of Categories objects.
     */
    public Vector<Categories> getAllCategories() {
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

    /**
     * It takes a string as a parameter and inserts it into the database
     * 
     * @param categoryName The name of the category to be inserted into the database.
     * @return The number of rows affected by the SQL statement.
     */
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

    /**
     * This function deletes a category from the database
     * 
     * @param id the id of the category to be deleted
     * @return The number of rows affected by the SQL statement.
     */
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

    /**
     * It returns a category object by a product ID
     * 
     * @param pid ProductID
     * @return A Categories object.
     */
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

    /**
     * It takes an integer as a parameter and returns a category object
     * 
     * @param cID the category ID
     * @return A category object.
     */
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
