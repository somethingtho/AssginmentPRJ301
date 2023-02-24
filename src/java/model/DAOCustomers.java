/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Customers;
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
public class DAOCustomers extends DBContext {

    public Customers LoginAdmin(String pass, String user) {
        Customers cus = null;
        String sql = "SELECT Customers.*, Accounts.UserName, Accounts.Role, Accounts.RegistrationDate, Accounts.Status FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID \n"
                + "WHERE UserName = ? AND Password = ?";
        DAOAccounts daoAccounts = new DAOAccounts();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
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

    public int LockCustomers(Customers c) {
        int number = 0;
        String sql = "UPDATE Accounts SET Status = ? WHERE ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, c.getAcc().isStatus());
            pre.setInt(2, c.getAcc().getId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

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

    public Customers getCustomerByEmail(String email) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE Email = ?";

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
                    cus = new Customers(customerID, gender, customerName, phone, email, address, id, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

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

    public Vector<Customers> getCustomerFriendlyBySupplier(int supplierID) {
        Vector<Customers> vector = new Vector<>();

        String sql = "SELECT TOP 5 Customers.*, b.TotalMoney FROM dbo.Customers INNER JOIN \n"
                + "(\n"
                + "SELECT CustomerID, a.TotalMoney FROM dbo.Orders INNER JOIN \n"
                + "(SELECT OrderID,SUM(UnitPrice*Quantity) AS TotalMoney FROM dbo.OrderDetails\n"
                + "WHERE ProductID IN(SELECT Products.ProductID FROM dbo.Products WHERE SupplierID = ?)\n"
                + "GROUP BY OrderID) AS A ON A.OrderID = Orders.OrderID\n"
                + ") AS b ON b.CustomerID = Customers.CustomerID ORDER BY b.TotalMoney DESC";

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

    //list all customers
    public Vector getAllCustomers() {
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

    //Detele Customers
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

    //Update customer
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

    public int InsertNewCustomers(Customers cus) {
        int number = 0;
        String sql1 = "INSERT INTO dbo.Accounts (UserName, Role, Status, RegistrationDate) VALUES (?, 3, 1, GETDATE() )";
        try {
            PreparedStatement pre1 = connection.prepareStatement(sql1);
            pre1.setString(1, cus.getAcc().getUserName());
            number = pre1.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql2 = "INSERT INTO dbo.Customers ( CustomerName, Gender, Phone, Email, Address, ID, Password, Image) VALUES(?, 1, NULL, ?, NULL, (SELECT ID FROM dbo.Accounts WHERE UserName = ?), ?, (SELECT * FROM OPENROWSET(BULK N'C:\images\customers\default.png', SINGLE_BLOB) as T1))";

        try {
            PreparedStatement pre2 = connection.prepareStatement(sql2);
            pre2.setString(1, cus.getAcc().getUserName());
            pre2.setString(2, cus.getEmail());
            pre2.setInt(3, cus.getAcc().getId());
            pre2.setString(4, cus.getPassword());
            number = pre2.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

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

    //Admin  
    public Vector getAllCustomersByAdmin() {
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

    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
//        Vector<Customers> vector = dao.getCustomerFriendlyBySupplier(1);
//        for (Customers customers : vector) {
//            System.out.println(customers);
//        }
        System.out.println(dao.getCustomerByEmail("daoson03112002@gmail.com"));
    }
}
