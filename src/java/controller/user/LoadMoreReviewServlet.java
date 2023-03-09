/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Review;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.DAOReview;

/**
 *
 * @author ADMIN
 */
public class LoadMoreReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet LoadMoreReviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadMoreReviewServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        DAOReview daoReview = new DAOReview();
        String total = request.getParameter("total");
        String pid = request.getParameter("pid");
        try {
            int amount = Integer.parseInt(total);
            int productID = Integer.parseInt(pid);
            Vector<Review> nextReview = daoReview.getNextReviewByProductID(productID, amount);
            for (Review review : nextReview) {
                out.print("<div class=\"media container\" style=\"margin-bottom: 40px;\">\n"
                        + "                <div class=\"media-left\">\n"
                        + "                    <img src=\"data:image/jpg;base64," + review.getCus().getBase64Image() + "\" class=\"media-object\" style=\"width:60px\">\n"
                        + "                </div>\n"
                        + "                <div class=\"media-body\">\n"
                        + "                    <h4 class=\"media-heading\">" + review.getAcc().getUserName() + "</h4><span>" + review.getDateRate() + "</span>\n"
                        + "                    <p>" + review.getContentSend() + "</p>\n");
                for (int i = 1; i <= review.getRate(); i++) {
                    out.print("<span class=\"fa fa-star checked\"></span>\n");
                }
                for (int i = 1; i <= 5-review.getRate(); i++) {
                    out.print("<span class=\"fa fa-star\"></span>\n");
                }
                out.print("</div>\n" +
"            </div>");
            }
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
        processRequest(request, response);
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
