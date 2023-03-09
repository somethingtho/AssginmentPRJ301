/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.util.function.Supplier;
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
public class UpdateSupplierServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateSupplierServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSupplierServlet at " + request.getContextPath() + "</h1>");
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
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        String supID_raw = request.getParameter("supid");

        try {
            int supID = Integer.parseInt(supID_raw);
            Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supID);

            Vector<Suppliers> listAllSuppliers = daoSuppliers.getAllSuppliers();
            request.setAttribute("supplier", supplier);
            request.setAttribute("listAllSuppliers", listAllSuppliers);

            request.getRequestDispatcher("formupsup.jsp").forward(request, response);
        } catch (Exception e) {
        }
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

        DAOSuppliers daoSuppliers = new DAOSuppliers();

        String companyName = request.getParameter("companyName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String homePage = request.getParameter("homePage");
        String status_raw = request.getParameter("status");
        String supplierID_raw = request.getParameter("supplierID");
        boolean status = status_raw.equals("ON");

        try {
            int supplierID = Integer.parseInt(supplierID_raw);
            Suppliers s = daoSuppliers.getSuppliersBySupplierID(supplierID);
            s.setCompanyName(companyName);
            s.setEmail(email);
            s.setHomePage(homePage);
            s.setPhone(phoneNumber);
            s.setStatus(status);
            if (daoSuppliers.UpdateSupplier(s) > 0) {
                request.setAttribute("message", "Cập nhật thành công!");
            }
            Vector<Suppliers> listAllSuppliers = daoSuppliers.getAllSuppliers();
            request.setAttribute("supplier", s);
            request.setAttribute("listAllSuppliers", listAllSuppliers);
            request.getRequestDispatcher("formupsup.jsp").forward(request, response);
        } catch (Exception e) {
        }

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
