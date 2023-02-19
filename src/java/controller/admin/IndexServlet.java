/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import entity.Customers;
import entity.Review;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategories;
import model.DAOCustomers;
import model.DAOFeedback;
import model.DAOOrderDetails;
import model.DAOOrders;
import model.DAOProducts;
import model.DAOReview;
import model.DAOShippers;
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
public class IndexServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IndexServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndexServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAOCustomers daoCustomer = new DAOCustomers();
        DAOCategories daoCategory = new DAOCategories();
        DAOSuppliers daoSupplier = new DAOSuppliers();
        DAOOrders daoOrders  = new DAOOrders();
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        DAOShippers daoShippers = new DAOShippers();
        DAOProducts daoProducts = new DAOProducts();
        DAOFeedback daoFeedback = new DAOFeedback();
        DAOReview daoReview = new DAOReview();
        
        int totalCustomers = daoCustomer.TotalCustomers();
        int totalCategories = daoCategory.TotalCategories();
        int totalSuppliers = daoSupplier.TotalSuppliers();
        int totalOrders = daoOrders.TotalOrders();
        int totalOrderDetails = daoOrderDetails.TotalOrderDetails();
        int totalShippers = daoShippers.TotalShippers();
        int totalProducts = daoProducts.TotalProducts();
        int totalFeedback = daoFeedback.TotalFeedbacks();
        
        Vector<Review> top5Review = daoReview.getTop5Review();
        Vector<Customers> newCustomers = daoCustomer.getNewCustomers();
        
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("totalCategories", totalCategories);
        request.setAttribute("totalSuppliers", totalSuppliers);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalOrderDetails", totalOrderDetails);
        request.setAttribute("totalShippers", totalShippers);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("totalFeedback", totalFeedback);
        request.setAttribute("top5Review", top5Review);
        request.setAttribute("newCustomers", newCustomers);
        
        
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
