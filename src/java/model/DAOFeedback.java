/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customers;
import entity.Feedback;
import entity.Shippers;
import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SendEmail;

/**
 *
 * @author daova
 */
public class DAOFeedback extends DBContext {

    /**
     * It returns a vector of all the emails in the Feedback table
     *
     * @return A vector of strings.
     */
    public Vector<String> getAllEmailFeedback() {
        Vector<String> vector = new Vector<>();
        String sql = "SELECT Email FROM Feedback GROUP BY Email";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                vector.add(rs.getString("Email"));
            }
        } catch (Exception e) {
        }
        return vector;
    }

    // Getting the last feedback from the database.
    public Vector<Feedback> getLastFeedback() {
        Vector<Feedback> vector = new Vector<>();
        DAOCustomers daoCustomer = new DAOCustomers();
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOShippers daoShippers = new DAOShippers();
        String sql = "Select TOP 1 * FROM Feedback WHERE email = ? ORDER BY DateSend DESC";

        Vector<String> listAllEmail = getAllEmailFeedback();

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            for (String string : listAllEmail) {
                pre.setString(1, string);
                ResultSet rs = pre.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    Integer idAccount = rs.getInt("IDAccount");
                    String contentSend = rs.getString("ContentSend");
                    String contentRep = rs.getString("ContentRep");
                    boolean status = rs.getBoolean("Status");
                    String email = rs.getString("Email");
                    int role = rs.getInt("Role");
                    String dateSend = rs.getString("DateSend");
                    String dateRep = rs.getString("DateRep");
                    if (idAccount == 0) {
                        if (role == 2) {
                            Suppliers supplier = daoSuppliers.GetSupplierByEmail(email);
                            vector.add(new Feedback(id, contentSend, contentRep, status, role, dateSend, dateRep, supplier));
                        }
                        if (role == 0) {
                            Shippers shipper = daoShippers.getShipperByEmail(email);
                            vector.add(new Feedback(id, contentSend, contentRep, status, role, dateSend, dateRep, shipper));
                        }
                        if (role == 3) {
                            Customers customer = daoCustomer.getCustomerByEmail(email);
                            vector.add(new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep));
                        }
                    } else {
                        Customers customer = daoCustomer.getCustomerByID(idAccount);
                        vector.add(new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Define a custom Comparator for sorting Feedback objects
        Comparator<Feedback> feedbackComparator = new Comparator<Feedback>() {
            public int compare(Feedback f1, Feedback f2) {
                // First, sort by status
                int statusCompare = Boolean.compare(f1.isStatus(), f2.isStatus());
                if (statusCompare != 0) {
                    return statusCompare;
                }

                // If statuses are the same, sort by dateSend and dateRep
                String dateSend1 = f1.getDateSend();
                String dateRep1 = f1.getDateRep();
                String dateSend2 = f2.getDateSend();
                String dateRep2 = f2.getDateRep();

                if (dateSend1 == null || dateSend2 == null) {
                    return 0; // cannot compare
                }

                int dateSendCompare = dateSend1.compareTo(dateSend2);
                if (dateSendCompare != 0) {
                    return -dateSendCompare;
                }

                if (dateRep1 == null || dateRep2 == null) {
                    return 0; // cannot compare
                }

                return -dateRep1.compareTo(dateRep2);
            }
        };

        Collections.sort(vector, feedbackComparator);

        return vector;
    }

    // Getting the last feedback from the database.
    public Vector<Feedback> getLastFeedbackByEmail(String e) {
        Vector<Feedback> vector = new Vector<>();
        DAOCustomers daoCustomer = new DAOCustomers();
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOShippers daoShippers = new DAOShippers();
        String sql = "Select TOP 1 * FROM Feedback WHERE email = ? ORDER BY DateSend DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, e);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                Integer idAccount = rs.getInt("IDAccount");
                String contentSend = rs.getString("ContentSend");
                String contentRep = rs.getString("ContentRep");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                int role = rs.getInt("Role");
                String dateSend = rs.getString("DateSend");
                String dateRep = rs.getString("DateRep");
                if (idAccount == 0) {
                    if (role == 2) {
                        Suppliers supplier = daoSuppliers.GetSupplierByEmail(email);
                        vector.add(new Feedback(id, contentSend, contentRep, status, role, dateSend, dateRep, supplier));
                    }
                    if (role == 0) {
                        Shippers shipper = daoShippers.getShipperByEmail(email);
                        vector.add(new Feedback(id, contentSend, contentRep, status, role, dateSend, dateRep, shipper));
                    }
                    if (role == 3) {
                        Customers customer = daoCustomer.getCustomerByEmail(email);
                        vector.add(new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep));
                    }
                } else {
                    Customers customer = daoCustomer.getCustomerByID(idAccount);
                    vector.add(new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Define a custom Comparator for sorting Feedback objects
        Comparator<Feedback> feedbackComparator = new Comparator<Feedback>() {
            public int compare(Feedback f1, Feedback f2) {
                // First, sort by status
                int statusCompare = Boolean.compare(f1.isStatus(), f2.isStatus());
                if (statusCompare != 0) {
                    return statusCompare;
                }

                // If statuses are the same, sort by dateSend and dateRep
                String dateSend1 = f1.getDateSend();
                String dateRep1 = f1.getDateRep();
                String dateSend2 = f2.getDateSend();
                String dateRep2 = f2.getDateRep();

                if (dateSend1 == null || dateSend2 == null) {
                    return 0; // cannot compare
                }

                int dateSendCompare = dateSend1.compareTo(dateSend2);
                if (dateSendCompare != 0) {
                    return -dateSendCompare;
                }

                if (dateRep1 == null || dateRep2 == null) {
                    return 0; // cannot compare
                }

                return -dateRep1.compareTo(dateRep2);
            }
        };

        Collections.sort(vector, feedbackComparator);

        return vector;
    }

    /**
     * It gets all feedbacks from the database by email
     *
     * @param f Feedback
     * @return A vector of feedback objects.
     */
    public Vector<Feedback> getAllFeedbackByEmail(Feedback f) {
        Vector<Feedback> vector = new Vector<>();
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOShippers daoShippers = new DAOShippers();
        DAOCustomers daoCustomer = new DAOCustomers();
        String sql = "SELECT * FROM Feedback WHERE Email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, f.getEmail());
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                Integer idAccount = rs.getInt("IDAccount");
                String contentSend = rs.getString("ContentSend");
                String contentRep = rs.getString("ContentRep");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                int role = rs.getInt("Role");
                String dateSend = rs.getString("DateSend");
                String dateRep = rs.getString("DateRep");
                if (idAccount == 0) {
                    if (role == 2) {
                        Suppliers supplier = daoSuppliers.GetSupplierByEmail(email);
                        vector.add(new Feedback(id, contentSend, contentRep, status, role, dateSend, dateRep, supplier));
                    }
                    if (role == 0) {
                        Shippers shipper = daoShippers.getShipperByEmail(email);
                        vector.add(new Feedback(id, contentSend, contentRep, status, role, dateSend, dateRep, shipper));
                    }
                    if (role == 3) {
                        Customers customer = daoCustomer.getCustomerByEmail(email);
                        vector.add(new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep));
                    }
                } else {
                    Customers customer = daoCustomer.getCustomerByID(idAccount);
                    vector.add(new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It's a function that gets a feedback by its ID
     *
     * @param idSearch the id of the feedback that I want to get
     * @return A Feedback object.
     */
    public Feedback getFeedbackByID(int idSearch) {
        Feedback f = null;
        DAOCustomers daoCustomer = new DAOCustomers();
        String sql = "SELECT * FROM Feedback WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, idSearch);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                Integer idAccount = rs.getInt("IDAccount");
                String contentSend = rs.getString("ContentSend");
                String contentRep = rs.getString("ContentRep");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                int role = rs.getInt("Role");
                String dateSend = rs.getString("DateSend");
                String dateRep = rs.getString("DateRep");
                if (idAccount != 0) {
                    Customers customer = daoCustomer.getCustomerByID(idAccount);
                    f = new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep);
                } else {
                    f = new Feedback(id, contentSend, contentRep, status, email, role, dateSend, dateRep);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return f;
    }

    /**
     * Update the feedback table in the database and send an email to the user
     *
     * @param feedback is an object of Feedback class
     * @param contentRep the content of the reply
     * @return The number of rows affected by the update.
     */
    public int UpdateFeedback(Feedback feedback, String contentRep) {
        SendEmail send = new SendEmail();
        int number = 0;
        String sql = "UPDATE Feedback SET ContentRep = ?, Status = 1, DateRep = GETDATE() WHERE ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, feedback.getContentRep());
            pre.setInt(2, feedback.getId());

            number = pre.executeUpdate();
            if (number > 0) {
                send.sendFeedBack(feedback.getEmail(), contentRep);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    /**
     * It returns the number of rows in the Feedback table
     *
     * @return The number of feedbacks in the database.
     */
    public int TotalFeedbacks() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Feedback";
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
     * This function is used to get all feedbacks from database and return a
     * vector of feedbacks
     *
     * @return A vector of Feedback objects.
     */
    public Vector<Feedback> getAllFeedBack() {
        DAOCustomers daoCustomer = new DAOCustomers();

        Vector<Feedback> vector = new Vector<>();
        String sql = "SELECT * FROM Feedback ORDER BY DateSend DESC";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idAccount = rs.getInt("IDAccount");
                String contentSend = rs.getString("ContentSend");
                String contentRep = rs.getString("ContentRep");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                int role = rs.getInt("Role");
                String dateSend = rs.getString("DateSend");
                String dateRep = rs.getString("DateRep");
                Customers customer = daoCustomer.getCustomerByEmail(email);
                if (customer != null) {
                    vector.add(new Feedback(id, contentSend, contentRep, status, customer, email, role, dateSend, dateRep));
                } else {
                    vector.add(new Feedback(id, contentSend, contentRep, status, email, role, dateSend, dateRep));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It inserts a new row into the database table "Feedback" with the values
     * of the parameters
     *
     * @param idAccount the id of the account
     * @param email String
     * @param contendSend the content of the feedback
     * @param role 1 = admin, 2 = user
     * @return The number of rows affected by the query.
     */
    public int AddFeedback(int idAccount, String email, String contendSend, int role) {
        int number = 0;
        String sql = "INSERT INTO dbo.Feedback(IDAccount, Email,ContentSend, Role,Status, DateSend, DateRep) VALUES(?, ?, ?, ?, 0, GETDATE(), NULL)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, idAccount);
            pre.setString(2, email);
            pre.setString(3, contendSend);
            pre.setInt(4, role);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * It inserts a new row into the database table "Feedback" with the values
     * of the parameters
     *
     * @param email String
     * @param contendSend the content of the feedback
     * @param role 1 = admin, 2 = user
     * @return The number of rows affected by the SQL statement.
     */
    public int AddFeedback(String email, String contendSend, int role) {
        int number = 0;
        String sql = "INSERT INTO dbo.Feedback(Email,ContentSend, Role,Status, DateSend, DateRep) VALUES(?, ?, ?, 0, GETDATE(), NULL)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, contendSend);
            pre.setInt(3, role);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * It deletes a row from the database table Feedback where the ID is equal
     * to the id parameter
     *
     * @param id the id of the feedback
     * @return The number of rows affected by the query.
     */
    public int DeleteFeedback(int id) {
        int number = 0;
        String sql = "DELETE FROM Feedback WHERE ID = ?";
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
     * UpdateShippers(Feedback feedback)
     *
     * @param feedback Feedback
     * @return The number of rows affected by the update.
     */
    public int UpdateShippers(Feedback feedback) {
        int number = 0;
        String sql = "UPDATE Feedback SET IDAccount = ?, ContentSend = ?, ContentRep = ?, Status WHERE ID = ?";
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, feedback.getIdAccount());
            pre.setString(2, feedback.getContentSend());
            pre.setString(2, feedback.getContentRep());
            pre.setBoolean(4, feedback.isStatus());
            pre.setInt(5, feedback.getId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public static void main(String[] args) {
        DAOFeedback dao = new DAOFeedback();
        Vector<String> v = dao.getAllEmailFeedback();
        for (String string : v) {
            System.out.println(string);
        }
//        Vector<Feedback> list = dao.getLastFeedback();
//        for (Feedback feedback : list) {
//            System.out.println(feedback.getEmail());
//        }
    }

}
