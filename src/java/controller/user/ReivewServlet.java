/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import entity.Accounts;
import entity.Customers;
import entity.Products;
import entity.Review;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOAccounts;
import model.DAOProducts;
import model.DAOReview;

/**
 *
 * @author daova
 */
public class ReivewServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ReivewServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReivewServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        PrintWriter out = response.getWriter();
        String contentSend = request.getParameter("contentSend");
        String star_raw = request.getParameter("rating");
        String productID_raw = request.getParameter("productID");
        HttpSession session = request.getSession();
        Customers cus = (Customers) session.getAttribute("account");
        String userName = cus.getAcc().getUserName();
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOReview daoReview = new DAOReview();
        DAOProducts daoProducts = new DAOProducts();
        try {
            int star = Integer.parseInt(star_raw);
            int productID = Integer.parseInt(productID_raw);
            Accounts acc = daoAccounts.getUsername(userName);
            Products product = daoProducts.getProductByID(productID);
            daoReview.InsertReview(new Review(acc, product, contentSend, star));
            response.sendRedirect("item?pid=" + productID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
