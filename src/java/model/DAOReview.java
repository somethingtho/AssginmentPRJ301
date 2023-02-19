/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Categories;
import entity.Customers;
import entity.Review;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
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
public class DAOReview extends DBContext {

    public int TotalReviewBySupplier(int supplierID) {
        int number = 0;
        String sql = "SELECT COUNT(Review.ProductID) FROM dbo.Review WHERE Review.ProductID IN(SELECT Products.ProductID FROM dbo.Products WHERE Products.SupplierID = ?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOReview.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }
    
    
    public Vector<Review> getTop5ReviewBySupplier(int supplierID) {
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT TOP 5 Review.*, Customers.Image, Customers.CustomerName, Accounts.UserName FROM dbo.Review INNER JOIN dbo.Accounts\n" +
"                ON Accounts.ID = Review.IDAccount INNER JOIN dbo.Customers ON Customers.ID = Accounts.ID\n" +
"				WHERE Review.ProductID IN (SELECT Products.ProductID FROM dbo.Products WHERE Products.SupplierID = ?)\n" +
"                ORDER BY Review.ID DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();  
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                int productID = rs.getInt("ProductID");
                String content = rs.getString("ContentSend");
                int rate = rs.getInt("Rate");
                String userName = rs.getString("UserName");
                String postDate = rs.getString("DateRate");
                String customerName = rs.getString("CustomerName");
                Accounts acc = new Accounts(userName);
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
                    Customers cus = new Customers(idAccount, customerName,base64Image);
                    vector.add(new Review(id, acc, cus, productID, content, rate, postDate));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOReview.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<Review> getTop5Review() {
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT TOP 5 Review.*, Customers.Image,Accounts.UserName FROM dbo.Review INNER JOIN dbo.Accounts \n"
                + "ON Accounts.ID = Review.IDAccount INNER JOIN dbo.Customers ON Customers.ID = Accounts.ID\n"
                + " ORDER BY Review.ID DESC";

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                int productID = rs.getInt("ProductID");
                String content = rs.getString("ContentSend");
                int rate = rs.getInt("Rate");
                String userName = rs.getString("UserName");
                String postDate = rs.getString("DateRate");
                Accounts acc = new Accounts(userName);
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
                    Customers cus = new Customers(idAccount, base64Image);
                    vector.add(new Review(id, acc, cus, productID, content, rate, postDate));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOReview.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<Review> getAllReviewByProductID(int pid) {
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT Review.*, Customers.Image,Accounts.UserName FROM dbo.Review INNER JOIN dbo.Accounts \n"
                + "ON Accounts.ID = Review.IDAccount INNER JOIN dbo.Customers ON Customers.ID = Accounts.ID\n"
                + "WHERE ProductID = ? AND Status = 1 ORDER BY Review.ID DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                int productID = pid;
                String content = rs.getString("ContentSend");
                int rate = rs.getInt("Rate");
                String userName = rs.getString("UserName");
                String postDate = rs.getString("DateRate");
                Accounts acc = new Accounts(userName);
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
                    Customers cus = new Customers(idAccount, base64Image);
                    vector.add(new Review(id, acc, cus, productID, content, rate, postDate));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public double getAverageRate(int pid) {
        double avgRate = 0;
        Vector<Integer> rateList = new Vector<>();
        String sql = "SELECT Rate FROM Review WHERE ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int rate = rs.getInt(1);
                rateList.add(rate);
            }

            for (Integer integer : rateList) {
                avgRate += integer;
            }
            if (rateList.size() != 0) {
                avgRate /= (rateList.size());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return avgRate;
    }

    public int InsertReview(Review review) {
        int number = 0;
        String sql = "INSERT INTO dbo.Review (IDAccount, ProductID, ContentSend, Rate, DateRate, Status) VALUES (?, ?, ?, ?, GETDATE(), 1)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, review.getAcc().getId());
            pre.setInt(2, review.getProductID());
            pre.setString(3, review.getContentSend());
            pre.setInt(4, review.getRate());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int TotalReviews() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Review";
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
        DAOReview dao = new DAOReview();
        Vector<Review> vector = dao.getTop5ReviewBySupplier(1);
        for (Review review : vector) {
            System.out.println(review);
        }
    }
}
