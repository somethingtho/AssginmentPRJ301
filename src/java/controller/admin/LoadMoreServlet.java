/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.DAOProducts;

/**
 *
 * @author ADMIN
 */
public class LoadMoreServlet extends HttpServlet {

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
            out.println("<title>Servlet LoadMoreServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadMoreServlet at " + request.getContextPath() + "</h1>");
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

        DAOProducts daoProdcuts = new DAOProducts();
        String amount = request.getParameter("total");
        int total = Integer.parseInt(amount);
        
        Vector<Products> vector = daoProdcuts.getNextProducts(total);
        for (Products products : vector) {
            out.print("<div class=\"col-lg-3 col-md-6\">\n"
                    + "                                <div class=\"card\">\n"
                    + "                                    <div class=\"el-card-item\">\n"
                    + "                                        <div class=\"el-card-avatar el-overlay-1\">\n"
                    + "                                            <img src=\"data:image/jpg;base64,"+products.getBase64Image()+"\" alt=\"user\" />\n"
                    + "                                            <div class=\"el-overlay\">\n"
                    + "                                                <ul class=\"list-style-none el-info\">\n"
                    + "                                                    <li class=\"el-item\">\n"
                    + "                                                        <a\n"
                    + "                                                            class=\"btn default btn-outline image-popup-vertical-fit el-link\"\n"
                    + "                                                            href=\"data:image/jpg;base64,"+products.getBase64Image()+"\"\n"
                    + "                                                            ><i class=\"mdi mdi-magnify-plus\"></i\n"
                    + "                                                            ></a>\n"
                    + "                                                    </li>\n"
                    + "                                                </ul>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <div\n"
                    + "                                            class=\"el-card-content\"\n"
                    + "                                            style=\"display: flex; flex-direction: column\"\n"
                    + "                                            >\n"
                    + "                                            <button\n"
                    + "                                                class=\"btn_sample\">\n"
                    + "                                                <a href=\"${pageContext.request.contextPath}/user/item?pid="+products.getProductID()+"\" class=\"mb-0\">(#"+products.getProductID()+") - "+products.getProductName()+"</a>\n"
                    + "                                            </button>\n"
                    + "                                            <button\n"
                    + "                                                class=\"btn_sample\">\n"
                    + "                                                <a href=\"${pageContext.request.contextPath}/admin/updateproduct?pid="+products.getProductID()+"\">Chỉnh sửa</a>\n"
                    + "                                            </button>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>");
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
