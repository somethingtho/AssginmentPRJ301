/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.*;
import model.DAOCustomers;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(maxFileSize = 16177215)

public class ProfileAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet ProfileAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileAdminServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("profileadmin.jsp");
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
        PrintWriter out = response.getWriter();
        DAOCustomers daoCustomers = new DAOCustomers();
        InputStream inputStream = null;
        HttpSession session = request.getSession();
        Part filePart = request.getPart("photo");

        if (filePart != null && filePart.getSize() > 0) {
            inputStream = filePart.getInputStream();
        }
        String address = request.getParameter("address");
        Customers cus = (Customers) session.getAttribute("admin");
        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        String gender_raw = request.getParameter("gender");
        String email = request.getParameter("email");
        boolean gender;
        Customers check = daoCustomers.getCustomerByEmail(email);
        Customers check2 = daoCustomers.getCustomerByPhone(phone);
        if (check != null && cus != null) {
            if (!check.getAcc().getUserName().equalsIgnoreCase(cus.getAcc().getUserName())) {
                request.setAttribute("error", "Email đã được sử dụng vui lòng chọn Email khác!");
                request.getRequestDispatcher("profileadmin.jsp").forward(request, response);
            }

        }

        if (check2 != null  && cus != null) {
            if (!check2.getAcc().getUserName().equalsIgnoreCase(cus.getAcc().getUserName())) {
                request.setAttribute("error", "Số điện thoại đã được sử dụng vui lòng chọn Số điện thoại khác!");
                request.getRequestDispatcher("profileadmin.jsp").forward(request, response);
            }
        }
        try {
            gender = gender_raw.equals("male");
            cus.setPhone(phone);
            cus.setAddress(address);
            cus.setCustomerName(customerName);
            cus.setEmail(email);
            cus.setGender(gender);
            daoCustomers.UpdateCustomers(cus, inputStream);
            Customers update = daoCustomers.getCustomerByUserName(cus.getAcc().getUserName());
            cus.setBase64Image(update.getBase64Image());
            session.setAttribute("admin", cus);
            request.setAttribute("error", "Cập nhật thành công!");
            request.getRequestDispatcher("profileadmin.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            String[] tokens = contentDisp.split(";");
            for (String token : tokens) {
                if (token.startsWith("filename")) {
                    return token.substring(token.indexOf("=") + 2, token.length() - 1);
                }
            }
        }
        return null;
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
