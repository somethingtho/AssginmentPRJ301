/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Orders;
import entity.Products;
import entity.Review;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.DAOOrders;
import model.DAOProducts;

/**
 *
 * @author ADMIN
 */
public class LoadMoreReviewsServlet extends HttpServlet {

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
            out.println("<title>Servlet LoadMoreReviewsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadMoreReviewsServlet at " + request.getContextPath() + "</h1>");
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
        DAOProducts daoProducts = new DAOProducts();
        PrintWriter out = response.getWriter();
        String total = request.getParameter("total");
        try {
            int amount = Integer.parseInt(total);
            Vector<Products> vector = daoProducts.getNextReviewsProducts(amount);
            for (Products products : vector) {
                out.print("<div class=\"products\">\n"
                        + "   <h4 class=\"\" style=\"margin-left: 20px\"><a href=\""+request.getContextPath() +"/user/item?pid=" + products.getProductID() + "\">" + products.getProductName() + "</a> </h4>\n");

                // Loop through the reviews and include them in the output
                for (Review review : products.getReviews()) {
                    out.print("<div class=\"d-flex flex-row comment-row mt-0\">\n"
                            + "   <div class=\"p-2\">\n"
                            + "      <img src=\"data:image/jpg;base64,"+review.getCus().getBase64Image()+"\" alt=\"user\" width=\"50\" class=\"rounded-circle\" />\n"
                            + "   </div>\n"
                            + "   <div class=\"comment-text w-100\">\n"
                            + "      <h6 class=\"font-medium\"><a href=\"" + request.getContextPath() +"/admin/profile?type=customer&id="+review.getCus().getCustomerID()+"\">"+review.getCus().getCustomerName()+"</a></h6>\n"
                            + "      <span class=\"mb-3 d-block\">("+review.getRate()+" <i class=\"fas fa-star\" style=\"color: gold;\"></i>)"+review.getContentSend()+"\n");
                            if(review.isStatus())out.print("<i class=\"mdi mdi-check-circle\"></i>\n");
                            if(!review.isStatus())out.print("<i class=\"mdi mdi-block-helper\"></i>\n");
                            out.print("      </span>\n"
                            + "      <div class=\"comment-footer\">\n"
                            + "         <span class=\"text-muted float-end\">"+review.getDateRate()+"</span>\n"
                            + "         <form action=\"\" method=\"POST\" id=\"review\" name=\"review\">\n"
                            + "            <button type=\"button\" onclick=\"changeReview('public', '"+review.getId()+"')\" class=\"  btn btn-success btn-sm text-white"); if(review.isStatus())out.print(" disabled");
                            out.print("\">\n"
                            + "               Publish\n"
                            + "            </button>\n"
                            + "            <button type=\"button\" onclick=\"changeReview('hidden', '"+review.getId()+"')\" class=\"btn btn-danger btn-sm text-white"); if(!review.isStatus())out.print(" disabled");out.print("\">\n"
                            + "               Hidden\n"
                            + "            </button>\n"
                            + "         </form>\n"
                            + "      </div>\n"
                            + "   </div>\n"
                            + "</div>\n");
                }

                out.print("<hr>\n"
                        + "</div>");
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
