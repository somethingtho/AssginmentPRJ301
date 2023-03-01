/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import java.sql.SQLException;
import model.DAOCustomers;
import model.DAOViews;

/**
 *
 * @author daova
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("login.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static int activeSessions = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAOCustomers daoCustomers = new DAOCustomers();
        DAOViews daoView = new DAOViews();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        Customers customer = daoCustomers.getCustomerByUserName(username, password);
        if (customer == null) {
            request.setAttribute("error", "Tài khoản không tồn tại hoặc mật khẩu chưa chính xác!!!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (!customer.getAcc().isStatus()) {
                request.setAttribute("error", "Tài khoản của bạn bị khoá vui lòng liên hệ Admin để xử lý!!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("account", customer);
                Cookie cuser = new Cookie("user", username);
                Cookie cpass = new Cookie("pass", password);
                Cookie cr = new Cookie("cr", remember);
                int loginCount = daoView.getLoginCountForCurrentDay();
                loginCount++;
                daoView.saveLoginCountForCurrentDay(loginCount);
                synchronized (this) {
                    activeSessions++;
                }

                System.out.println(activeSessions);
                if (remember == null) {
                    cuser.setMaxAge(0);
                    cpass.setMaxAge(0);
                    cr.setMaxAge(0);
                } else {
                    cuser.setMaxAge(60 * 60 * 60);
                    cpass.setMaxAge(60 * 60 * 60);
                    cr.setMaxAge(60 * 60 * 60);
                }
                response.addCookie(cr);
                response.addCookie(cuser);
                response.addCookie(cpass);
                response.sendRedirect("index");
            }

        }

    }

    public void sessionDestroyed(HttpSessionEvent event) {
        synchronized (this) {
            activeSessions--;
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
