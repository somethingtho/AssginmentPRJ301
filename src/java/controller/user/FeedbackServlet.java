/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Accounts;
import entity.Customers;
import entity.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.DAOAccounts;
import model.DAOCustomers;
import model.DAOFeedback;
import util.SendEmail;

/**
 *
 * @author ADMIN
 */
public class FeedbackServlet extends HttpServlet {

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
            out.println("<title>Servlet FeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackServlet at " + request.getContextPath() + "</h1>");
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
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String txt = "Xin chào! Cảm ơn bạn đã gửi phản hồi cho chúng tôi. "
    + "Chúng tôi gửi thư này để xác nhận rằng chúng tôi đã nhận được phản hồi từ bạn! Chúng tôi sẽ sớm hồi âm! "
    + "Xin chân thành cảm ơn về đóng góp của bạn! Chúc bạn có một ngày mới tốt lành!!";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String contentSend = request.getParameter("contentSend");
            String role_raw = request.getParameter("role");
            int role = Integer.parseInt(role_raw);
            DAOFeedback daoFeedback = new DAOFeedback();
            HttpSession session = request.getSession();
            Customers cus = (Customers) session.getAttribute("account");
            SendEmail send = new SendEmail();
            if (cus != null) {
                int n = daoFeedback.AddFeedback(cus.getAcc().getId(), cus.getEmail(), contentSend, cus.getAcc().getRole());
                if (n > 0) {
                    request.setAttribute("msg", "Phản hồi của bạn đã được chúng tôi ghi lại! Xin chân thành cảm ơn!");
                    send.sendVerifiedFeedBack(email, txt);
                } else request.setAttribute("msg", "Error");
            } else {
                int n = daoFeedback.AddFeedback(email, contentSend, role);
                if (n > 0) {
                    request.setAttribute("msg", "Phản hồi của bạn đã được chúng tôi ghi lại! \n"
                            + "Xin chân thành cảm ơn!");
                    send.sendVerifiedFeedBack(email, txt);
                } else request.setAttribute("msg", "Error");
            }
            request.getRequestDispatcher("help.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
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
