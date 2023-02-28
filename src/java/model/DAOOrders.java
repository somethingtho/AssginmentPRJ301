/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Cart;
import entity.Customers;
import entity.Item;
import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import entity.Shippers;
import java.io.IOException;
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
public class DAOOrders extends DBContext {
    
    /**
     * It returns the number of orders that have been placed in the current month
     * 
     * @return The number of orders in the current month.
     */
    public int newOrdersInMonth(){
        int number = 0;
        
        String sql = "SELECT COUNT(*) AS Number FROM Orders WHERE MONTH(OrderDate) = MONTH(GETDATE()) AND Status = 1";
        
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                number = rs.getInt("Number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }
    
    /**
     * It returns the rate of new orders in a month
     * 
     * @return The rate of new orders in a month.
     */
    public double rateNewOrders(){
        double rate = 0;
        int total = 0;
        String sql = "SELECT COUNT(*) AS Number FROM Orders ";
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                total = rs.getInt("Number");
            }
            if(total != 0){
                rate = (double)newOrdersInMonth()/total;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rate;
    }
    

    /**
     * It returns a vector of integers that represent the number of orders for each month of the year
     * 
     * @param year the year that you want to get the number of orders
     * @return A vector of integers.
     */
    public Vector<Integer> NumberOrdersByMonth(int year) {
        Vector<Integer> vector = new Vector<>();

        String sql = "SELECT MONTH(OrderDate),COUNT(OrderID) AS Number FROM dbo.Orders WHERE YEAR(OrderDate) = ? GROUP BY MONTH(OrderDate) ORDER BY MONTH(OrderDate) ASC";

        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, year);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                vector.add(rs.getInt("Number"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * I want to get the total money of all orders of a customer
     * 
     * @param cid customer id
     * @return The total money of all orders of a customer.
     */
    public double getTotalMoneyByCustomerID(int cid) {
        double money = 0;
        DAOShippers daoShippers = new DAOShippers();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOOrderDetails daoOrderDetail = new DAOOrderDetails();
        Vector<Orders> vector = new Vector<>();
        String sql = "SELECT * FROM Orders WHERE CustomerID = ? AND Status = 1 ORDER BY OrderDate DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                boolean payments = rs.getBoolean("Payments");
                boolean status = rs.getBoolean("Status");
                Vector<OrderDetails> listOrderDetail = daoOrderDetail.getAllOrderDetailsByOrderID(orderID);
                Shippers shipper = daoShippers.getShipperByShipperID(shipVia);
                Customers customer = daoCustomers.getCustomerByCustomerID(customerID);
                double totalMoney = getTotalMoneyByOrderID(orderID);
                vector.add(new Orders(orderID, orderDate, requiredDate, shippedDate, shipper, shipAddress, payments, status, listOrderDetail, totalMoney, customer));
            }

            for (Orders orders : vector) {
                money += orders.getTotalMoney();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return money;
    }

    /**
     * It returns the number of orders made by a customer with a given id
     * 
     * @param id the customer id
     * @return The number of orders by a customer.
     */
    public int TotalOrdersByCustomers(int id) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.Orders INNER JOIN dbo.Customers ON Customers.CustomerID = Orders.CustomerID\n"
                + "WHERE Customers.CustomerID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
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
     * I want to get all orders by supplierID, but I have to get the orderDetails, shipper, customer,
     * totalMoney of each order
     * 
     * @param sid SupplierID
     * @return A vector of Orders
     */
    public Vector<Orders> getOrderBySupplier(int sid) {
        Vector<Orders> vector = new Vector<>();
        DAOShippers daoShipper = new DAOShippers();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        String sql = "SELECT DISTINCT(Orders.OrderID),Orders.CustomerID, Orders.OrderDate, Orders.RequiredDate, Orders.ShippedDate, Orders.ShipVia, Orders.ShipAddress, Orders.Payments, Orders.Status  \n"
                + "FROM dbo.OrderDetails INNER JOIN dbo.Products ON Products.ProductID = OrderDetails.ProductID\n"
                + "INNER JOIN dbo.Orders ON Orders.OrderID = OrderDetails.OrderID\n"
                + "WHERE SupplierID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                boolean payments = rs.getBoolean("Payments");
                boolean status = rs.getBoolean("Status");
                Shippers shipper = daoShipper.getShipperByShipperID(shipVia);
                Customers cus = daoCustomers.getCustomerByCustomerID(customerID);
                Vector<OrderDetails> orderDetails = daoOrderDetails.getAllOrderDetailsByOrderID(oid);
                double totalMoney = getTotalMoneyByOrderID(oid);
                vector.add(new Orders(oid, orderDate, requiredDate, shippedDate, shipper, shipAddress, payments, status, orderDetails, totalMoney, cus));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }

    /**
     * It returns the number of distinct customers who have placed orders with a particular shipper
     * 
     * @param shipperID the ID of the shipper
     * @return The number of users that have used a specific shipper.
     */
    public int TotalUsersByShipper(int shipperID) {
        int number = 0;

        String sql = "SELECT COUNT(DISTINCT(CustomerID)) FROM dbo.Orders WHERE ShipVia =?";

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
     * This function is used to get all orders by shipperID
     * 
     * @param shipperID the ID of the shipper
     * @return A vector of Orders
     */
    public Vector<Orders> getOrderByShipperID(int shipperID) {
        DAOShippers daoShipper = new DAOShippers();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        Vector<Orders> vector = new Vector<>();
        String sql = "SELECT * FROM Orders WHERE ShipVia =? ORDER BY OrderDate DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                boolean payments = rs.getBoolean("Payments");
                boolean status = rs.getBoolean("Status");
                Shippers shipper = daoShipper.getShipperByShipperID(shipVia);
                Customers cus = daoCustomers.getCustomerByCustomerID(customerID);
                double totalMoney = getTotalMoneyByOrderID(oid);
                vector.add(new Orders(oid, orderDate, requiredDate, shippedDate, shipper, shipAddress, payments, status, totalMoney, cus));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It returns the number of orders that have not been shipped yet, and are assigned to a specific
     * shipper
     * 
     * @param shipperID the ID of the shipper
     * @return The number of orders that have failed.
     */
    public int TotalOrderFailByShipper(int shipperID) {
        int number = 0;

        String sql = "SELECT COUNT(*) FROM dbo.Orders WHERE Status = 0 AND ShipVia =?";

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
     * It returns the number of orders that have been shipped by a specific shipper
     * 
     * @param shipperID the ID of the shipper
     * @return The number of orders that have been successfully delivered by a particular shipper.
     */
    public int TotalOrderSuccessByShipper(int shipperID) {
        int number = 0;

        String sql = "SELECT COUNT(*) FROM dbo.Orders WHERE Status = 1 AND ShipVia =?";

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
     * It returns the number of orders that have been shipped by a particular shipper
     * 
     * @param shipperID the ID of the shipper
     * @return The number of orders that have been shipped by a particular shipper.
     */
    public int TotalOrdersByShipper(int shipperID) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.Orders WHERE ShipVia =?";

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
     * It returns the total money of a supplier by getting the list of orderID of the supplier, then
     * get the total money of each orderID and sum them up
     * 
     * @param supplierID the supplier ID
     * @return The total money of all orders that contain products from a specific supplier.
     */
    public double TotalMoneyBySupplier(int supplierID) {
        double money = 0;
        String sql = "SELECT DISTINCT(OrderDetails.OrderID) FROM dbo.OrderDetails WHERE OrderDetails.ProductID IN (SELECT Products.ProductID FROM dbo.Products WHERE SupplierID = ?)";
        Vector<Integer> listOrderID = new Vector<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                listOrderID.add(orderID);
            }

            for (Integer integer : listOrderID) {
                money += getTotalMoneyByOrderID(integer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return money;
    }

    /**
     * It returns the number of orders that contain at least one product from a given supplier
     * 
     * @param supplierID the ID of the supplier
     * @return The number of orders that have been placed for a particular supplier.
     */
    public int TotalOrderBySupplier(int supplierID) {
        int number = 0;
        String sql = "SELECT COUNT(DISTINCT(OrderDetails.OrderID)) FROM dbo.OrderDetails WHERE OrderDetails.ProductID IN (SELECT Products.ProductID FROM dbo.Products WHERE SupplierID = ?)";

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
     * It returns the number of orders in the database
     * 
     * @return The number of orders in the database.
     */
    public int TotalOrders() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Orders";
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
     * It gets all the orders from the database and returns them as a vector
     * 
     * @return A vector of Orders objects.
     */
    public Vector<Orders> getAllOrders() {
        DAOShippers daoShippers = new DAOShippers();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        Vector<Orders> vector = new Vector<>();
        String sql = "SELECT * FROM Orders";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String address = rs.getString("ShipAddress");
                boolean payments = rs.getBoolean("Payments");
                boolean status = rs.getBoolean("Status");
                Shippers shipper = daoShippers.getShipperByShipperID(shipVia);
                Customers customer = daoCustomers.getCustomerByCustomerID(customerID);
                Vector<OrderDetails> orderDetails = daoOrderDetails.getAllOrderDetailsByOrderID(id);
                double totalMoney = getTotalMoneyByOrderID(id);
                vector.add(new Orders(id, orderDate, requiredDate, shippedDate, shipper, address, payments, status, orderDetails, totalMoney, customer));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * This function is used to get all orders of a customer by customer ID
     * 
     * @param cid customer id
     * @return A vector of Orders
     */
    public Vector<Orders> getOrdersByCustomerID(int cid) {
        DAOShippers daoShippers = new DAOShippers();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOOrderDetails daoOrderDetail = new DAOOrderDetails();
        Vector<Orders> vector = new Vector<>();
        String sql = "SELECT * FROM Orders WHERE CustomerID = ? ORDER BY OrderDate DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                boolean payments = rs.getBoolean("Payments");
                boolean status = rs.getBoolean("Status");
                Vector<OrderDetails> listOrderDetail = daoOrderDetail.getAllOrderDetailsByOrderID(orderID);
                Shippers shipper = daoShippers.getShipperByShipperID(shipVia);
                Customers customer = daoCustomers.getCustomerByCustomerID(customerID);
                double totalMoney = getTotalMoneyByOrderID(orderID);
                vector.add(new Orders(orderID, orderDate, requiredDate, shippedDate, shipper, shipAddress, payments, status, listOrderDetail, totalMoney, customer));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * This function deletes an order from the database
     * 
     * @param id the id of the order to be deleted
     * @return The number of rows affected by the SQL statement.
     */
    public int DeleteOrders(int id) {
        int number = 0;
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
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
     * The function is used to add an order to the database
     * 
     * @param c Customer object
     * @param cart the cart object
     * @param shipperID the ID of the shipper
     * @param payments true or false
     * @param status true or false
     * @param required the date the customer wants the order to be delivered
     * @return The number of rows affected by the query.
     */
    public int addOrder(Customers c, Cart cart, int shipperID, boolean payments, boolean status, String required) {
        int number = 0;
        try {
            //add order
            String sql = "INSERT INTO dbo.Orders (CustomerID, OrderDate, RequiredDate, ShippedDate, ShipVia, ShipAddress, Payments, Status)\n"
                    + "VALUES (?, GETDATE(), ?, ?,  ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getCustomerID());
            st.setString(2, required);
            st.setString(3, required);
            st.setInt(4, shipperID);
            st.setString(5, c.getAddress());
            st.setBoolean(6, payments);
            st.setBoolean(7, status);
            number = st.executeUpdate();
            String sql1 = "select top 1 OrderID from orders order by orderid desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add bang OrderDetail
            if (rs.next()) {
                int oid = rs.getInt("OrderID");
                for (Item i : cart.getItems()) {
                    String sql2 = "INSERT INTO dbo.OrderDetails (OrderID, ProductID, UnitPrice, Quantity, Discount) VALUES (?, ?,  ?, ?, 0.1)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getProductID());
                    st2.setDouble(3, i.getPrice());
                    st2.setInt(4, i.getQuantity());
                    st2.executeUpdate();
                }
            }
            //cap nhat lai so luong san pham
            String sql3 = "update products set UnitsInStock = UnitsInStock-?, UnitsOnOrders = UnitsOnOrders + ? where ProductID=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getQuantity());
                st3.setInt(3, i.getProduct().getProductID());
                st3.executeUpdate();
            }
        } catch (SQLException e) {

        }
        return number;
    }

    /**
     * The function is used to add an order to the database
     * 
     * @param c Customer object
     * @param cart the cart object
     * @param shipperID the ID of the shipper
     * @param payments true or false
     * @param status true
     * @return The number of rows affected by the query.
     */
    public int addOrder(Customers c, Cart cart, int shipperID, boolean payments, boolean status) {
        int number = 0;
        try {
            //add order
            String sql = "INSERT INTO dbo.Orders (CustomerID, OrderDate, RequiredDate, ShippedDate, ShipVia, ShipAddress, Payments, Status)\n"
                    + "VALUES (?, GETDATE(), GETDATE()+3, GETDATE()+3,  ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getCustomerID());
            st.setInt(2, shipperID);
            st.setString(3, c.getAddress());
            st.setBoolean(4, payments);
            st.setBoolean(5, status);
            number = st.executeUpdate();
            String sql1 = "select top 1 OrderID from orders order by orderid desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add bang OrderDetail
            if (rs.next()) {
                int oid = rs.getInt("OrderID");
                for (Item i : cart.getItems()) {
                    String sql2 = "INSERT INTO dbo.OrderDetails (OrderID, ProductID, UnitPrice, Quantity, Discount) VALUES (?, ?,  ?, ?, ?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getProductID());
                    st2.setDouble(3, i.getPrice());
                    st2.setInt(4, i.getQuantity());
                    st2.setDouble(5, i.getPrice() * 1.1 - i.getPrice());
                    st2.executeUpdate();
                }
            }
            //cap nhat lai so luong san pham
            String sql3 = "update products set UnitsInStock = UnitsInStock-?, UnitsOnOrders = UnitsOnOrders + ? where ProductID=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getQuantity());
                st3.setInt(3, i.getProduct().getProductID());
                st3.executeUpdate();
            }
        } catch (SQLException e) {

        }
        return number;
    }

    /**
     * This function is used to get an order by orderID
     * 
     * @param oID OrderID
     * @return A list of orders.
     */
    public Orders getOrdersByOrderID(int oID) {
        DAOShippers daoShippers = new DAOShippers();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOOrderDetails daoOrderDetail = new DAOOrderDetails();
        Orders order = null;
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, oID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                boolean payments = rs.getBoolean("Payments");
                boolean status = rs.getBoolean("Status");
                Vector<OrderDetails> listOrderDetail = daoOrderDetail.getAllOrderDetailsByOrderID(orderID);
                Shippers shipper = daoShippers.getShipperByShipperID(shipVia);
                double totalMoney = getTotalMoneyByOrderID(orderID);
                Customers customer = daoCustomers.getCustomerByCustomerID(customerID);
                order = new Orders(orderID, orderDate, requiredDate, shippedDate, shipper, shipAddress, payments, status, listOrderDetail, totalMoney, customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    /**
     * It gets the total money of an order by its ID
     * 
     * @param oID Order ID
     * @return The total amount of money spent on an order.
     */
    public double getTotalMoneyByOrderID(int oID) {
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        double total = 0;
        Vector<OrderDetails> orderDetail = daoOrderDetails.getAllOrderDetailsByOrderID(oID);
        for (OrderDetails orderDetails : orderDetail) {
            total += orderDetails.getUnitPrice();
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        DAOOrders dao = new DAOOrders();
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        Vector<OrderDetails> odd = daoOrderDetails.getAllOrderDetailsByOrderID(1);

        Vector<Orders> list = dao.getOrderBySupplier(1);
        for (OrderDetails orders : odd) {
            System.out.println(orders);
        }
    }
}
