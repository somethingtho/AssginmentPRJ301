/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Accounts;
import entity.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOAccounts;
import model.DAOCustomers;

/**
 *
 * @author daova
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("register.jsp");
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
        DAOAccounts daoAccounts = new DAOAccounts();
        DAOCustomers daoCustomers = new DAOCustomers();
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String cfpass = request.getParameter("cfpassword");
        String email = request.getParameter("email");

        if (user == null) {
            request.setAttribute("error", "Tên đăng nhập không được bỏ trống vui lòng nhập lại!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
        Accounts acc = daoAccounts.getUsername(user);
        Customers cusTest = daoCustomers.getCustomerByEmail(email);
        if (acc != null) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại vui lòng chọn tên khác!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        if(cusTest!= null){
            request.setAttribute("error", "Email đã tồn tại vui lòng nhập Email khác!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }else {
            if (!pass.equals(cfpass)) {
                request.setAttribute("error", "Mật khẩu không khớp. Vui lòng nhập lại!!!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }else{
                Accounts newAcc = new Accounts(user);
                Customers cus = new Customers(user, email, newAcc, pass);
                int n = daoCustomers.InsertNewCustomers(cus);
                if(n >= 2) request.setAttribute("error", "Đăng ký thành công giờ bạn có thể đăng nhập!!!");
                else request.setAttribute("error", "Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
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
