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
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
public class AddSupplierServlet extends HttpServlet {
   
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
            out.println("<title>Servlet AddSupplierServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSupplierServlet at " + request.getContextPath () + "</h1>");
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
        DAOSuppliers daoSupplier = new DAOSuppliers();
        Vector<Suppliers> listAllSup = daoSupplier.getAllSuppliers();
        
        request.setAttribute("listAllSup", listAllSup);
        
        request.getRequestDispatcher("formaddsup.jsp").forward(request, response);
        
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
        DAOSuppliers dao = new DAOSuppliers();
        String companyName = request.getParameter("companyname");
        String phone = request.getParameter("phone");
        String hompage = request.getParameter("homepage");
        String status_raw = request.getParameter("status");
        String email = request.getParameter("email");
        boolean status = status_raw.equals("ON");
        
        Suppliers sup1 = dao.GetSupplierByCompanyName(companyName);
        Suppliers sup2 = dao.GetSupplierByPhone(phone);
        Suppliers sup3 = dao.GetSupplierByEmail(email);
        Suppliers sup4 = dao.GetSupplierByHomePage(hompage);
        if(sup1 != null || sup2 != null || sup3 != null || sup4 != null){
            request.setAttribute("message", "Thông tin đã tồn tại");
        }else{
            Suppliers s = new Suppliers(companyName, phone, email, hompage, status);
            if(dao.AddNewSupplier(s) > 0) request.setAttribute("message", "Thêm thương hiệu mới thành công!");
        }
        Vector<Suppliers> listAllSup = dao.getAllSuppliers();
        request.setAttribute("listAllSup", listAllSup);
        request.getRequestDispatcher("formaddsup.jsp").forward(request, response);
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
