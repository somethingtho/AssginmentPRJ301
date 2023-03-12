/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Customers;
import entity.Products;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SendEmail;

/**
 *
 * @author daova
 */
public class DAOCustomers extends DBContext {

    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
        System.out.println(dao.rateOrders(2));
    }

    /**
     * I get all customers from database, then I get total money of each
     * customer, then I put them into a map, then I sort the map by value, then
     * I return the map
     *
     * @return A map of customers and their total money spent.
     */
    public Vector<Customers> getTop5Customers() {
        DAOOrders daoOrders = new DAOOrders();
        DAOAccounts daoAccounts = new DAOAccounts();
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM dbo.Customers INNER JOIN \n"
                + "(SELECT CustomerID, SUM(TotalMoney) AS TotalMoney FROM dbo.Orders WHERE Status = 1 GROUP BY CustomerID) AS a \n"
                + "ON a.CustomerID = Customers.CustomerID ORDER BY TotalMoney DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    double totalMoney = rs.getDouble("TotalMoney");
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, daoAccounts.getID(id), id, password, base64Image, totalMoney));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

    /**
     * It returns the rate of orders that are completed by a customer
     *
     * @param id CustomerID
     * @return The rate of orders that are completed.
     */
    public double rateOrders(int id) {
        double rate = 0;
        DAOOrders daoOrders = new DAOOrders();

        String sql = "SELECT Status, COUNT(*) AS Number FROM dbo.Orders INNER JOIN dbo.Customers ON Customers.CustomerID = Orders.CustomerID\n"
                + "WHERE Customers.CustomerID = ?\n"
                + "GROUP BY Status";

        HashMap<Integer, Integer> numbers = new HashMap<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int status = rs.getInt("Status");
                Integer number = rs.getInt("Number");
                numbers.put(status, number);
            }
            int total = daoOrders.TotalOrdersByCustomers(id);
            System.out.println(total);
            if (total != 0) {
                for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
                    Integer key = entry.getKey();
                    Integer val = entry.getValue();
                    System.out.println(key);
                    System.out.println(val);
                    if (key == 1) {
                        rate = (double) val / total;
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rate;
    }

    /**
     * It takes a vector of customers, a start index, and an end index, and
     * returns a vector of customers
     *
     * @param vector the vector of customers
     * @param start the index of the first element in the vector to be returned
     * @param end the end index of the vector
     * @return A vector of customers
     */
    public Vector<Customers> getListByPage(Vector<Customers> vector,
            int start, int end) {
        Vector<Customers> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(vector.get(i));
        }
        return arr;
    }

    /**
     * It's a function that returns a customer object, which contains an account
     * object, which contains a role, username, password, registration date, and
     * status.
     * </code>
     *
     * @param pass the password of the account
     * @param user admin
     * @return A Customers object.
     */
    public Customers LoginAdmin(String user, String pass) {
        Customers cus = null;
        String sql = "SELECT Customers.*, Accounts.UserName, Accounts.Role, Accounts.RegistrationDate, Accounts.Status FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID \n"
                + "WHERE UserName = ? AND Password = ?";
        DAOAccounts daoAccounts = new DAOAccounts();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                int id = rs.getInt("ID");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    Accounts acc = daoAccounts.getID(id);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cus;

    }

    /**
     * It's a function that locks or unlocks an account
     *
     * @param c is the object of the class Customers
     * @return The number of rows affected by the update.
     */
    public int LockCustomers(Customers c) {
        SendEmail send = new SendEmail();
        int number = 0;
        String sql = "UPDATE Accounts SET Status = ? WHERE ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, c.getAcc().isStatus());
            pre.setInt(2, c.getAcc().getId());
            number = pre.executeUpdate();
            if (number > 0 && !c.getAcc().isStatus()) {
                send.sendLockAccount(c.getEmail());
            }
            if (number > 0 && c.getAcc().isStatus()) {
                send.sendUnLockAccount(c.getEmail());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * It gets the customer's information from the database and returns it as a
     * Customer object
     *
     * @param id the id of the customer
     * @return A Customers object.
     */
    public Customers getCustomerByID(int id) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE ID  = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    cus = new Customers(customerID, gender, customerName, phone, email, address, id, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    /**
     * I get the image from the database, convert it to a byte array, then
     * convert it to a base64 string, then return the customer object
     *
     * @param email String
     * @return A Customers object.
     */
    public Customers getCustomerByPhone(String email) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE Phone = ?";
        DAOAccounts daoAccounts = new DAOAccounts();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    Accounts acc = daoAccounts.getID(id);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    /**
     * I get the image from the database, convert it to a byte array, then
     * convert it to a base64 string, then return the customer object
     *
     * @param email String
     * @return A Customers object.
     */
    public Customers getCustomerByEmail(String email) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE Email = ?";
        DAOAccounts daoAccounts = new DAOAccounts();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    Accounts acc = daoAccounts.getID(id);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    /**
     * I'm trying to get the image from the database and convert it to base64
     * string to display it on the web page.
     * </code>
     *
     * @param cid customerID
     * @return A Customers object.
     */
    public Customers getCustomerByCustomerID(int cid) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE CustomerID = ?";
        DAOAccounts daoAccounts = new DAOAccounts();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    Accounts acc = daoAccounts.getID(id);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    /**
     * It gets a list of customers who have bought from a specific supplier
     *
     * @param supplierID int
     * @return A vector of Customers
     */
    public Vector<Customers> getCustomerFriendlyBySupplier(int supplierID) {
        Vector<Customers> vector = new Vector<>();

        String sql = "EXEC Customer_Friendly_Suppliers @supid = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");
                double totalMoney = rs.getDouble("TotalMoney");
                Accounts acc = null;
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image, totalMoney));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * I get the data from the database, then I convert the image from blob to
     * base64 and add it to the vector
     *
     * @return A vector of Customers objects.
     */
    public Vector<Customers> getNewCustomers() {
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID ORDER BY RegistrationDate DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                String username = rs.getString("UserName");
                int role = rs.getInt("Role");
                String dateRegis = rs.getString("RegistrationDate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = new Accounts(id, username, role, dateRegis, status);
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It gets all the customers from the database and returns them as a vector
     *
     * @return A vector of Customers objects.
     */
    public Vector<Customers> getAllCustomers() {
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT * FROM Customers";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, id, password, base64Image));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * The function takes an integer as an argument and deletes the row in the
     * Customers table where the CustomerID is equal to the argument
     *
     * @param id the id of the customer to be deleted
     * @return The number of rows affected by the SQL statement.
     */
    public int DeleteCustomers(int id) {
        int number = 0;
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";
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
     * The function is used to update the customer's information in the database
     *
     * @param customer is a Customer object
     * @param file the image file
     * @return The number of rows affected by the update.
     */
    public int UpdateCustomers(Customers customer, InputStream file) {
        int number = 0;
        String sql = "UPDATE Customers SET CustomerName = ?, Phone = ?, Email = ?, Address = ?,Gender = ? WHERE CustomerID = ?";
        try {
            if (file != null) {
                sql = "UPDATE Customers SET CustomerName = ?, Phone = ?, Email = ?, Address = ?,Gender = ?, Image = ? WHERE CustomerID = ?";
            }
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, customer.getCustomerName());
            pre.setString(2, customer.getPhone());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getAddress());
            pre.setBoolean(5, customer.isGender());
            if (file != null) {
                pre.setBlob(6, file);
                pre.setInt(7, customer.getCustomerID());
                number = pre.executeUpdate();
            } else {
                pre.setInt(6, customer.getCustomerID());
                number = pre.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    /**
     * It gets the customer's information from the database and returns it as a
     * Customers object
     *
     * @param userName String
     * @param password 123456
     * @return A Customers object.
     */
    public Customers getCustomerByUserName(String userName, String password) {
        Customers cus = null;
        String sql = "SELECT Customers.*, Accounts.UserName, Accounts.Role, Accounts.RegistrationDate, Accounts.Status  FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID\n"
                + "WHERE UserName = ? AND Password = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                int role = rs.getInt("Role");
                boolean status = rs.getBoolean("Status");
                String registerDate = rs.getString("RegistrationDate");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Accounts acc = new Accounts(id, userName, role, registerDate, status);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cus;
    }

    /**
     * It gets the customer's information from the database and returns it as a
     * Customers object.
     * </code>
     *
     * @param userName String
     * @return A Customers object.
     */
    public Customers getCustomerByUserName(String userName) {
        Customers cus = null;
        String sql = "SELECT Customers.*, Accounts.UserName, Accounts.Role, Accounts.RegistrationDate, Accounts.Status  FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID\n"
                + "WHERE UserName = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, userName);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                int role = rs.getInt("Role");
                boolean status = rs.getBoolean("Status");
                String password = rs.getString("Password");
                String registerDate = rs.getString("RegistrationDate");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Accounts acc = new Accounts(id, userName, role, registerDate, status);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cus;
    }

    /**
     * Insert a new customer into the database
     *
     * @param cus is a Customers object
     * @return The number of rows affected by the SQL statement.
     */
    public int InsertNewCustomers(Customers cus) {
        SendEmail send = new SendEmail();
        int number = 0;
        String sql1 = "INSERT INTO dbo.Accounts (UserName, Role, Status, RegistrationDate) VALUES (?, 3, 1, GETDATE() )";
        try {
            PreparedStatement pre1 = connection.prepareStatement(sql1);
            pre1.setString(1, cus.getAcc().getUserName());
            number = pre1.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql2 = "INSERT INTO dbo.Customers (CustomerName, Email, ID, Password, Image) "
                + "VALUES(?, ?, (SELECT ID FROM Accounts WHERE UserName = ?), ?,"
                + " (SELECT * FROM OPENROWSET(BULK N'C:/images/customers/default.png', SINGLE_BLOB) as T1))";

        try {
            PreparedStatement pre2 = connection.prepareStatement(sql2);
            pre2.setString(1, cus.getAcc().getUserName());
            pre2.setString(2, cus.getEmail());
            pre2.setString(3, cus.getAcc().getUserName());
            pre2.setString(4, cus.getPassword());
            number += pre2.executeUpdate();
            if (number >= 2) {
                send.sendRegister(cus.getEmail());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    /**
     * It updates the password of a customer in the database
     *
     * @param cus is the object of the class Customers
     * @return The number of rows affected by the update.
     */
    public int ChangePassword(Customers cus) {
        String sql = "UPDATE Customers SET Password = ? Where CustomerID = ?";
        int number = 0;
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cus.getPassword());
            pre.setInt(2, cus.getCustomerID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * I want to get all the data from the Customers table and the Accounts
     * table, then put them into a vector
     *
     * @return A vector of Customers objects.
     */
    public Vector<Customers> getAllCustomersByAdmin() {
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                String username = rs.getString("UserName");
                int role = rs.getInt("Role");
                String dateRegis = rs.getString("RegistrationDate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = new Accounts(id, username, role, dateRegis, status);
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It returns the number of customers in the database
     *
     * @return The number of customers in the database.
     */
    public int TotalCustomers() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Customers";
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

}
