/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.DAOAccounts;
import model.DAOCategories;
import model.DAOCustomers;
import model.DAOProducts;
import model.DAOShippers;
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
public class TablesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TablesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TablesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOCategories daoCategories = new DAOCategories();
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOShippers daoShippers = new DAOShippers();
        DAOProducts daoProducts = new DAOProducts();
        DAOCustomers daoCustomers = new DAOCustomers();

        Vector<Categories> listAllCategories = daoCategories.getAllCategories();
        Vector<Suppliers> listAllSuppliers = daoSuppliers.getAllSuppliers();
        Vector<Shippers> listAllShippers = daoShippers.getAllShippersByAdmin();
        Vector<Customers> listAllCustomers = daoCustomers.getAllCustomers();
        Vector<Products> listAllProducts = daoProducts.getAllProducts();

        request.setAttribute("listAllProducts", listAllProducts);
        request.setAttribute("listAllCustomers", listAllCustomers);
        request.setAttribute("listAllShippers", listAllShippers);
        request.setAttribute("listAllSuppliers", listAllSuppliers);
        request.setAttribute("listAllCategories", listAllCategories);
        
        
        
        request.getRequestDispatcher("tables.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
