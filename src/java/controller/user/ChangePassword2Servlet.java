/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Cart;
import entity.Customers;
import entity.Products;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.DAOCustomers;
import model.DAOProducts;
import model.DAOShippers;
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
public class ChangePassword2Servlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword2Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword2Servlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOProducts daoProducts = new DAOProducts();
        DAOShippers daoShippers = new DAOShippers();

        Vector<Suppliers> listAllSuppliersSmartPhone = daoSuppliers.getAllSuppliersSmartPhone();
        Vector<Suppliers> listAllSuppliersLaptop = daoSuppliers.getAllSuppliersLaptop();
        Vector<Suppliers> listAllSuppliersTablet = daoSuppliers.getAllSuppliersTablet();

        request.setAttribute("listAllSuppliersSmartPhone", listAllSuppliersSmartPhone);
        request.setAttribute("listAllSuppliersLaptop", listAllSuppliersLaptop);
        request.setAttribute("listAllSuppliersTablet", listAllSuppliersTablet);
        Vector<Products> list = daoProducts.getAllProducts();

        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, list);
        request.setAttribute("cart", cart);
        request.setAttribute("size", cart.getItems().size());
        String newPassword = request.getParameter("newPassword").trim();
        String cfNewPassword = request.getParameter("cfNewPassword").trim();
        String email = request.getParameter("email").trim();
        HttpSession session = request.getSession();
        DAOCustomers daoCustomers = new DAOCustomers();
        Customers cus = daoCustomers.getCustomerByEmail(email);

        if (!newPassword.equals(cfNewPassword)) {
            request.setAttribute("error", "Hai mật khẩu mới không giống nhau!!!");
            request.getRequestDispatcher("changepassword2.jsp").forward(request, response);
        } else {
            cus.setPassword(newPassword);
            int number = daoCustomers.ChangePassword(cus);
            if (number > 0) {
                request.setAttribute("error", "Thay đổi mật khẩu thành công bạn có thể mua hàng!!!");
            }
            session.setAttribute("account", cus);
            request.getRequestDispatcher("changepassword2.jsp").forward(request, response);
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
