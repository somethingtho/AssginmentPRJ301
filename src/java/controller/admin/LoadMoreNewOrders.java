/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.*;
import model.DAOOrders;

/**
 *
 * @author ADMIN
 */
public class LoadMoreNewOrders extends HttpServlet {

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
            out.println("<title>Servlet LoadMoreNewOrders</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadMoreNewOrders at " + request.getContextPath() + "</h1>");
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
        DAOOrders daoOrders = new DAOOrders();
        String total = request.getParameter("total");
        Locale locale = new Locale("vi", "VN"); // Create a Vietnamese locale
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        try {
            int amount = Integer.parseInt(total);
            Vector<Orders> vector = daoOrders.getNextNewOrders(amount);
            for (Orders orders : vector) {
                out.print("<div class=\"d-flex flex-row comment-row mt-0 orders\">\n"
                        + "                                                <div class=\"p-2\">\n"
                        + "                                                    <img\n"
                        + "                                                        src=\"data:image/jpg;base64," + orders.getCus().getBase64Image() + "\"\n"
                        + "                                                        alt=\"user\"\n"
                        + "                                                        width=\"50\"\n"
                        + "                                                        class=\"rounded-circle\"\n"
                        + "                                                        />\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"comment-text w-100\">\n"
                        + "                                                    <a href=\"" + request.getContextPath() + "/admin/profile?type=customer&id=" + orders.getCus().getCustomerID() + "\">" + orders.getCus().getCustomerName() + "</a>\n"
                        + "                                                    <span class=\"mb-3 d-block\">\n"
                        + "                                                        <a href=\"" + request.getContextPath() + "/admin/orderdetail?id=" + orders.getCus().getCustomerID() + "&oid=" + orders.getOrderID() + "\">OrderID: " + orders.getOrderID() + "</a><br>\n"
                        + "                                                        OrderDate: " + orders.getOrderDate() + "<br>\n"
                        + "                                                        RequiredDate: " + orders.getRequireDate() + "<br>\n"
                        + "                                                        Total Products: " + orders.getOrderDetails().size() + "<br>\n"
                        + "                                                        TotalMoney: " + format.format(orders.getTotalMoney()) + " <br>\n"
                        + "                                                    </span>\n"
                        + "                                                    <div class=\"comment-footer\">\n"
                        + "                                                        <span class=\"text-muted float-end\">" + orders.getOrderDate() + "</span>\n"
                        + "                                                        <button\n"
                        + "                                                            type=\"button\"\n"
                        + "                                                            class=\"btn btn-cyan btn-sm text-white\"\n"
                        + "                                                            >\n"
                        + "                                                            <a class=\"text-white\" href=\"" + request.getContextPath() + "/admin/orderdetail?id=" + orders.getCus().getCustomerID() + "&oid=" + orders.getOrderID() + "\">\n"
                        + "                                                                Information \n"
                        + "                                                            </a>\n"
                        + "                                                        </button>\n"
                        + "                                                        <button\n"
                        + "                                                            type=\"button\"\n"
                        + "                                                            onclick=\"updateOrder(" + orders.getOrderID() + ", 'accept')\"\n"
                        + "                                                            class=\"btn btn-success btn-sm text-white\"\n"
                        + "                                                            >\n"
                        + "                                                            Accept\n"
                        + "                                                        </button>\n"
                        + "                                                        <button\n"
                        + "                                                            type=\"button\"\n"
                        + "                                                            onclick=\"updateOrder(" + orders.getOrderID() + ", 'reject')\"\n"
                        + "                                                            class=\"btn btn-danger btn-sm text-white\"\n"
                        + "                                                            >\n"
                        + "                                                            Reject\n"
                        + "                                                        </button>\n"
                        + "                                                    </div>\n"
                        + "                                                </div>\n"
                        + "                                            </div> ");
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
