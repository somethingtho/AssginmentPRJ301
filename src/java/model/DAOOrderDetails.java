/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
import entity.Orders;
import entity.Products;
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
public class DAOOrderDetails extends DBContext{
    
    /**
     * This function returns the total number of products shipped by a specific shipper
     * 
     * @param shipperID the ID of the shipper
     * @return The number of products that have been shipped by a particular shipper.
     */
    public int TotalProductsByShipper(int shipperID){
        int number = 0;
        
        String sql = "SELECT COUNT(*) FROM dbo.OrderDetails WHERE OrderID IN(SELECT OrderID FROM dbo.Orders WHERE ShipVia =?)";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
    }
    
    /**
     * It returns the number of products sold by a supplier
     * 
     * @param supplierID the ID of the supplier
     * @return The number of products sold by a supplier.
     */
    public int TotalProductsSaleBySupplier(int supplierID){
        int number = 0;
        String sql = "SELECT COUNT(OrderDetails.ProductID) FROM dbo.OrderDetails WHERE OrderDetails.ProductID IN (SELECT Products.ProductID FROM dbo.Products WHERE SupplierID = ?)";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return number;
    }
    
    /**
     * It returns the number of rows in the OrderDetails table
     * 
     * @return The number of rows in the OrderDetails table.
     */
    public int TotalOrderDetails(){
        int number = 0;
        String sql = "SELECT COUNT(*) FROM OrderDetails";
        ResultSet rs= getData(sql);
        try {
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    /**
     * It returns a vector of OrderDetails objects, which are created from the data in the OrderDetails
     * table in the database
     * 
     * @param oID OrderID
     * @return A vector of OrderDetails objects.
     */
    public Vector<OrderDetails> getAllOrderDetailsByOrderID(int oID){
        Vector<OrderDetails> vector = new Vector<>();
        DAOProducts daoProducts = new DAOProducts();
        String sql = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, oID);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                int orderID = rs.getInt("OrderID");
                int productID = rs.getInt("ProductID");
                double unitPrice = rs.getDouble("UnitPrice");
                int quantity = rs.getInt("Quantity");
                double discount = rs.getDouble("Discount");
                Products product = daoProducts.getProductByID(productID);
                vector.add(new OrderDetails(orderID, product, unitPrice, quantity, discount));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return vector;
    }
    
    /**
     * This function takes a vector of orders and returns a vector of order details
     * 
     * @param orders A vector of Orders objects
     * @return A vector of OrderDetails objects.
     */
    public Vector<OrderDetails> getAllOrderDetails(Vector<Orders> orders){
        Vector<OrderDetails> vector = new Vector<>();
        for (int i = 0; i < orders.size(); i++) {
            vector.addAll(getAllOrderDetailsByOrderID(orders.get(i).getOrderID()));
        }
        return vector;
    }
    
    /**
     * Delete an order detail from the database
     * 
     * @param orderId the order id of the order that the user wants to delete
     * @param productID 1
     * @return The number of rows affected by the query.
     */
    public int DeleteOrderDetails(int orderId, int productID) {
        int number = 0;
        String sql = "DELETE FROM OrderDetails WHERE OrderID = ? AND ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, orderId);
            pre.setInt(2, productID);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    
    
    public static void main(String[] args) {
        DAOOrderDetails dao = new DAOOrderDetails();
        DAOOrders daoO = new DAOOrders();
        System.out.println(dao.TotalProductsByShipper(1));
    }
}
