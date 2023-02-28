/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.ProductInfo;
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
public class DAOProductInfo extends DBContext {
    public static void main(String[] args) {
        DAOProductInfo dao = new DAOProductInfo();
        ProductInfo info = dao.getProductInfoByProductID(1);
        info.setProductID(1093);
        System.out.println(dao.InsertProductInfo(info));
    }

    /**
     * Insert a new product into the database
     * 
     * @param info ProductInfo
     * @return The number of rows affected by the SQL statement.
     */
    public int InsertProductInfo(ProductInfo info) {
        int number = 0;
        String sql = "INSERT INTO dbo.ProductInfo(ProductID, Size, Weight, Substance, CPU, RAM, Screen, Camera, GraphicsCard, HardDrive, OS, BatteryCapacity, DateCreated)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, info.getProductID());
            pre.setString(2, info.getSize());
            pre.setString(3, info.getWeight());
            pre.setString(4, info.getSubstance());
            pre.setString(5, info.getCpu());
            pre.setString(6, info.getRam());
            pre.setString(7, info.getScreen());
            pre.setString(8, info.getCamera());
            pre.setString(9, info.getGraphicsCard());
            pre.setString(10, info.getHardDrive());
            pre.setString(11, info.getOs());
            pre.setString(12, info.getBatteryCapacity());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * It gets all the data from the ProductInfo table and puts it into a vector
     * 
     * @return A vector of ProductInfo objects.
     */
    public Vector<ProductInfo> getAllProductInfo() {
        Vector<ProductInfo> vector = new Vector<>();
        String sql = "SELECT * FROM ProductInfo";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String size = rs.getString("Size");
                String weight = rs.getString("Weight");
                String substance = rs.getString("Substance");
                String CPU = rs.getString("CPU");
                String RAM = rs.getString("RAM");
                String Screen = rs.getString("Screen");
                String Camera = rs.getString("Camera");
                String GraphicsCard = rs.getString("GraphicsCard");
                String HardDrive = rs.getString("HardDrive");
                String OS = rs.getString("OS");
                String BatteryCapacity = rs.getString("BatteryCapacity");
                String dateCreated = rs.getString("DateCreated");
                vector.add(new ProductInfo(productID, size, weight, substance, CPU, RAM, Screen, Camera, GraphicsCard, HardDrive, OS, BatteryCapacity, dateCreated));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }


    /**
     * This function deletes a product from the database
     * 
     * @param id the id of the product
     * @return The number of rows affected by the query.
     */
    public int DeleteProductInfo(int id) {
        int number = 0;
        String sql = "DELETE FROM ProductInfo WHERE ID = ?";
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
     * Update the product information in the database
     * 
     * @param info ProductInfo
     * @return The number of rows affected by the update.
     */
    public int UpdateProductInfo(ProductInfo info) {
        int number = 0;
        String sql = "UPDATE ProductInfo SET Size = ?, Weight = ?, Substance =? "
                + ", CPU =?, RAM = ?, Screen = ?, Camera = ?, GraphicsCard = ?, "
                + "HardDrive = ?, OS = ?, BatteryCapacity = ? WHERE ProductID = ?";
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, info.getSize());
            pre.setString(2, info.getWeight());
            pre.setString(3, info.getSubstance());
            pre.setString(4, info.getCpu());
            pre.setString(5, info.getRam());
            pre.setString(6, info.getScreen());
            pre.setString(7, info.getCamera());
            pre.setString(8, info.getGraphicsCard());
            pre.setString(9, info.getHardDrive());
            pre.setString(10, info.getOs());
            pre.setString(11, info.getBatteryCapacity());
            pre.setInt(12, info.getProductID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * It gets the product info by product ID
     * 
     * @param pid productID
     * @return A ProductInfo object.
     */
    public ProductInfo getProductInfoByProductID(int pid) {
        ProductInfo pinfo = null;
        String sql = "SELECT * FROM ProductInfo WHERE ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt("ProductID");
                String size = rs.getString("Size");
                String weight = rs.getString("Weight");
                String substance = rs.getString("Substance");
                String CPU = rs.getString("CPU");
                String RAM = rs.getString("RAM");
                String Screen = rs.getString("Screen");
                String Camera = rs.getString("Camera");
                String GraphicsCard = rs.getString("GraphicsCard");
                String HardDrive = rs.getString("HardDrive");
                String OS = rs.getString("OS");
                String BatteryCapacity = rs.getString("BatteryCapacity");
                String dateCreated = rs.getString("DateCreated");
                pinfo = new ProductInfo(productID, size, weight, substance, CPU, RAM, Screen, Camera, GraphicsCard, HardDrive, OS, BatteryCapacity, dateCreated);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pinfo;
    }

}
