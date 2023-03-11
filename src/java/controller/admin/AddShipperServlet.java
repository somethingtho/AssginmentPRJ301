/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Shippers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOShippers;

/**
 *
 * @author ADMIN
 */
public class AddShipperServlet extends HttpServlet {

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
            out.println("<title>Servlet AddShipperServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddShipperServlet at " + request.getContextPath() + "</h1>");
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
        DAOShippers daoShippers = new DAOShippers();
        Vector<Shippers> listAllShippers = daoShippers.getAllShippersByAdmin();
        request.setAttribute("listAllShippers", listAllShippers);
        request.getRequestDispatcher("formaddshipper.jsp").forward(request, response);
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
        String companyname = request.getParameter("companyname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String status_raw = request.getParameter("status");
        DAOShippers dao = new DAOShippers();
        boolean status;
        try {
            status = status_raw.equals("ON");
            Shippers s1 = dao.getShipperByCompanyName(companyname);
            Shippers s2 = dao.getShipperByEmail(companyname);
            Shippers s3 = dao.getShipperByPhone(companyname);
            if (s1 != null || s2 != null || s3 != null) {
                request.setAttribute("message", "Thông tin đã đã tồn tại!");
            } else {
                Shippers ship = new Shippers(companyname, phone, email, status);
                if (dao.AddNewShipper(ship) > 0) {
                    request.setAttribute("message", "Thêm đơn vị vận chuyển thành công");
                }
            }
            Vector<Shippers> listAllShippers = dao.getAllShippersByAdmin();
            request.setAttribute("listAllShippers", listAllShippers);
            request.getRequestDispatcher("formaddshipper.jsp").forward(request, response);
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
