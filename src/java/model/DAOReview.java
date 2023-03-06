/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Categories;
import entity.Customers;
import entity.Products;
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
import util.SendEmail;

/**
 *
 * @author daova
 */
public class DAOReview extends DBContext {

    /**
     * This function deletes a review from the database
     * 
     * @param id the id of the review to be deleted
     * @return The number of rows affected by the delete statement.
     */
    public int deleteReview(int id){
        int number  = 0;
        String sql ="DELETE Review WHERE id = ?";
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
     * This function is used to update the review of a product
     * 
     * @param r Review object
     * @return The number of rows affected by the update.
     */
    public int updateReview(Review r){
        int number = 0;
        
        String sql ="UPDATE Review SET ContentSend = ?, Rate = ? WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, r.getContentSend());
            pre.setInt(2, r.getRate());
            pre.setInt(3, r.getId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    /**
     * It gets a review by its ID
     *
     * @param rid the id of the review
     * @return A Review object.
     */
    public Review getReviewByID(int rid) {
        Review r = null;
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOProducts daoProducts = new DAOProducts();
        DAOCustomers daoCustomers = new DAOCustomers();

        String sql = "SELECT * FROM Review WHERE Review.ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, rid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                int productID = rs.getInt("ProductID");
                String content = rs.getString("ContentSend");
                int rate = rs.getInt("Rate");
                String postDate = rs.getString("DateRate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = daoAccounts.getID(idAccount);
                Customers customer = daoCustomers.getCustomerByUserName(acc.getUserName());
                Products product = daoProducts.getProductByID(productID);
                r = new Review(id, acc, customer, product, content, rate, postDate, status);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return r;
    }

    /**
     * I want to send an email to the customer when the admin changes the status
     * of the review from public to hidden
     *
     * @param r Review object
     * @param type public or hidden
     * @return The number of rows affected by the SQL statement.
     */
    public int changeReview(Review r, String type) {
        SendEmail send = new SendEmail();
        int number = 0;
        String sql = "UPDATE Review SET Status = ? WHERE ID = ?";
        boolean status = type.equals("public");
        String txt = "Hello! We send this email to inform that your comment at " + r.getDateRate() + " with content \"" + r.getContentSend() + "\" (#" + r.getPro().getProductID() + ")~" + r.getPro().getProductName()
                + ". Violation of our standards so we decided to hide this comment!"
                + " If you have any questions, please reply to this message to get it resolved! Thank you very much!";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, status);
            pre.setInt(2, r.getId());
            number = pre.executeUpdate();
            if (number > 0 && !status) {
                send.sendHiddenReview(r.getCus().getEmail(), txt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(txt);
        return number;

    }

    /**
     * It returns the number of rows in the Review table
     * 
     * @return The number of reviews in the database.
     */
    public int TotalReview() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Review";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
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
     * It returns the number of reviews for a product
     * 
     * @param pid product id
     * @return The number of reviews for a product.
     */
    public int TotalReviewByUser(int pid) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Review WHERE Status = 1 AND ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
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
     * It returns the number of reviews for a supplier.
     *
     * @param supplierID the ID of the supplier
     * @return The number of reviews for a specific supplier.
     */
    public int TotalReviewBySupplier(int supplierID) {
        int number = 0;
        String sql = "SELECT COUNT(Review.ProductID) FROM dbo.Review WHERE Review.ProductID IN(SELECT Products.ProductID FROM dbo.Products WHERE Products.SupplierID = ?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOReview.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

    /**
     * It gets the top 5 reviews from the database and returns a vector of
     * Review objects
     *
     * @return A vector of Review objects.
     */
    public Vector<Review> getTop5Review() {
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOProducts daoProducts = new DAOProducts();
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM dbo.Review ORDER BY Review.ID DESC";

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                boolean status = rs.getBoolean("Status");
                int idAccount = rs.getInt("IDAccount");
                int productID = rs.getInt("ProductID");
                String content = rs.getString("ContentSend");
                int rate = rs.getInt("Rate");
                String postDate = rs.getString("DateRate");
                Accounts acc = daoAccounts.getID(idAccount);
                Products product = daoProducts.getProductByID(productID);
                Customers cus = daoCustomers.getCustomerByUserName(acc.getUserName());
                vector.add(new Review(id, acc, cus, product, content, rate, postDate, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It gets all the reviews of a product by its ID
     *
     * @param pid product id
     * @return A vector of Review objects.
     */
    public Vector<Review> getAllReviewByProductID(int pid) {
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOProducts daoProducts = new DAOProducts();
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM dbo.Review WHERE ProductID = ? AND Review.Status = 1 ORDER BY Review.ID DESC";
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
                String postDate = rs.getString("DateRate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = daoAccounts.getID(idAccount);
                Customers cus = daoCustomers.getCustomerByUserName(acc.getUserName());
                Products product = daoProducts.getProductByID(productID);
                vector.add(new Review(id, acc, cus, product, content, rate, postDate, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    
    /**
     * I want to get all reviews of a product by productID and accountID
     * 
     * @param pid product id
     * @param accountID the ID of the account that is currently logged in
     * @return A vector of reviews.
     */
    public Vector<Review> getAllReviewByProductIDAndAccount(int pid, int accountID) {
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOProducts daoProducts = new DAOProducts();
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Review WHERE ProductID = ? AND Review.Status = 1 AND IDAccount =? ORDER BY Review.ID DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            pre.setInt(2, accountID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                int productID = pid;
                String content = rs.getString("ContentSend");
                int rate = rs.getInt("Rate");
                String postDate = rs.getString("DateRate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = daoAccounts.getID(idAccount);
                Customers cus = daoCustomers.getCustomerByUserName(acc.getUserName());
                Products product = daoProducts.getProductByID(productID);
                vector.add(new Review(id, acc, cus, product, content, rate, postDate, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    /**
     * It gets the next 5 reviews of a product by its ID, and the amount of reviews that have been
     * loaded
     * 
     * @param pid product id
     * @param amount the number of reviews that have been loaded
     * @return A vector of Review objects.
     */
    public Vector<Review> getNextReviewByProductID(int pid, int amount) {
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOProducts daoProducts = new DAOProducts();
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Review WHERE ProductID = ? AND Review.Status = 1 ORDER BY Review.ID DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            pre.setInt(2, amount);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                int productID = pid;
                String content = rs.getString("ContentSend");
                int rate = rs.getInt("Rate");
                String postDate = rs.getString("DateRate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = daoAccounts.getID(idAccount);
                Customers cus = daoCustomers.getCustomerByUserName(acc.getUserName());
                Products product = daoProducts.getProductByID(productID);
                vector.add(new Review(id, acc, cus, product, content, rate, postDate, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It returns a vector of integers, which contains the number of reviews of a product with a
     * certain rate
     * 
     * @param pid product id
     * @return A vector of integers.
     */
    public Vector<Integer> numberReviewtarProductID(int pid) {
        Vector<Integer> vector = new Vector<>();
        String sql = "SELECT COUNT(*) FROM dbo.Review WHERE ProductID = ? AND Review.Status = 1 AND Rate = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            for (int i = 5; i >= 1; i--) {
                pre.setInt(2, i);
                ResultSet rs = pre.executeQuery();
                if (rs.next()) {
                    vector.add(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * This function is used to get all reviews of a product by product ID and admin
     * 
     * @param pid product id
     * @return A vector of Review objects.
     */
    public Vector<Review> getAllReviewByProductIDAndAdmin(int pid) {
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOProducts daoProducts = new DAOProducts();
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Review WHERE ProductID = ? ORDER BY Review.ID DESC";
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
                String postDate = rs.getString("DateRate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = daoAccounts.getID(idAccount);
                Customers cus = daoCustomers.getCustomerByUserName(acc.getUserName());
                Products product = daoProducts.getProductByID(productID);
                vector.add(new Review(id, acc, cus, product, content, rate, postDate, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets the average rate of a product by getting the rate of each review
     * of that product and then dividing the sum of all the rates by the number
     * of reviews
     *
     * @param pid product id
     * @return The average rate of a product.
     */
    public double getAverageRate(int pid) {
        double avgRate = 0;
        Vector<Integer> rateList = new Vector<>();
        String sql = "SELECT Rate FROM Review WHERE ProductID = ? AND Status = 1";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int rate = rs.getInt(1);
                rateList.add(rate);
            }
            System.out.println(rateList.size());
            for (Integer integer : rateList) {
                avgRate += integer;
            }
            if (rateList.size() != 0) {
                avgRate /= (rateList.size());
            }
            System.out.println("List");
            for (Integer integer : rateList) {
                System.out.println(integer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return avgRate;
    }

    /**
     * Insert a review into the database
     *
     * @param review the object that contains the data to be inserted into the
     * database
     * @return The number of rows affected by the SQL statement.
     */
    public int InsertReview(Review review) {
        int number = 0;
        String sql = "INSERT INTO dbo.Review (IDAccount, ProductID, ContentSend, Rate, DateRate, Status) VALUES (?, ?, ?, ?, GETDATE(), 1)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, review.getAcc().getId());
            pre.setInt(2, review.getPro().getProductID());
            pre.setString(3, review.getContentSend());
            pre.setInt(4, review.getRate());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * This function returns the total number of reviews in the database
     *
     * @return The number of reviews in the database.
     */
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
        System.out.println(dao.deleteReview(61));
    }
}
