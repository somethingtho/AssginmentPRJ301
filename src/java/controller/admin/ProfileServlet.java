/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Categories;
import entity.Customers;
import entity.Orders;
import entity.Products;
import entity.Shippers;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.DAOCategories;
import model.DAOCustomers;
import model.DAOOrderDetails;
import model.DAOOrders;
import model.DAOProducts;
import model.DAOShippers;
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
public class ProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");
        String id_raw = request.getParameter("id");
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOShippers daoShippers = new DAOShippers();
        DAOCustomers daoCustomers = new DAOCustomers();
        DAOCategories daoCategories = new DAOCategories();
        DAOOrders daoOrders = new DAOOrders();
        DAOProducts daoProducts = new DAOProducts();
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        try {
            int id = Integer.parseInt(id_raw);
            if (type.equals("sup")) {
                Suppliers entity = daoSuppliers.getSuppliersBySupplierID(id);
                if (!entity.isStatus()) {
                    request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                }
                int totalCategoriesBySupplier = daoCategories.ToalCategoriesBySuppliers(id);
                int totalCategories = daoCategories.TotalCategories();
                int totalOrders = daoOrders.TotalOrderBySupplier(id);
                int totalProductSale = daoOrderDetails.TotalProductsSaleBySupplier(id);
                int totalProductsBySupplier = daoProducts.TotalProductsBySupplier(id);
                double totalMoney = daoOrders.TotalMoneyBySupplier(id);
                Vector<Orders> listAllOrders = daoOrders.getOrderBySupplier(id);
                Vector<Products> productBestSale = daoProducts.getProductBestSaleBySupplier(id);
                Vector<Categories> numberProductsOfCategory = daoSuppliers.NumberOfProductsBySupplier(id);
                
                
                request.setAttribute("numberProductsOfCategory", numberProductsOfCategory);
                request.setAttribute("productBestSale", productBestSale);
                request.setAttribute("totalCategoriesBySupplier", totalCategoriesBySupplier);
                request.setAttribute("totalCategories", totalCategories);
                request.setAttribute("totalOrders", totalOrders);
                request.setAttribute("totalProductSale", totalProductSale);
                request.setAttribute("totalProductsBySupplier", totalProductsBySupplier);
                request.setAttribute("totalMoney", totalMoney);
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                request.setAttribute("listAllOrders", listAllOrders);
                request.getRequestDispatcher("profileview.jsp").forward(request, response);
            }
            if (type.equals("ship")) {
                Shippers entity = daoShippers.getShipperByShipperID(id);
                if (!entity.isStatus()) {
                    request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                }

                int totalSuppliersByShipper = daoSuppliers.TotalSuppliersByShipper(id);
                int totalSuppliers = daoSuppliers.TotalSuppliers();
                int totalOrders = daoOrders.TotalOrdersByShipper(id);
                int totalProducts = daoOrderDetails.TotalProductsByShipper(id);
                int totalOrderSuccess = daoOrders.TotalOrderSuccessByShipper(id);
                int totalOrderFail = daoOrders.TotalOrderFailByShipper(id);
                Vector<Suppliers> listSuppliers = daoSuppliers.GetNumberProductsByShipper(id);
                Vector<Orders> listOrdersByShipperID = daoOrders.getOrderByShipperID(id);
                int numberOrderLate = daoOrders.NumberOrderLaterByShipper(id);

                request.setAttribute("numberOrderLate", numberOrderLate);
                request.setAttribute("listSuppliers", listSuppliers);
                request.setAttribute("totalOrderSuccess", totalOrderSuccess);
                request.setAttribute("totalOrderFail", totalOrderFail);
                request.setAttribute("totalProducts", totalProducts);
                request.setAttribute("totalOrders", totalOrders);
                request.setAttribute("totalSuppliers", totalSuppliers);
                request.setAttribute("listOrdersByShipperID", listOrdersByShipperID);
                request.setAttribute("totalSuppliersByShipper", totalSuppliersByShipper);
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                request.getRequestDispatcher("profileview.jsp").forward(request, response);
            }
            if (type.equals("customer")) {
                Customers entity = daoCustomers.getCustomerByCustomerID(id);
                if (!entity.getAcc().isStatus()) {
                    request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                }

                Vector<Orders> listAllOrders = daoOrders.getOrdersByCustomerID(id);
                double rate = daoCustomers.rateOrders(id);
                double total = daoOrders.getTotalMoneyByCustomerID(id);

                request.setAttribute("total", total);
                request.setAttribute("rate", rate);
                request.setAttribute("listAllOrders", listAllOrders);
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                request.getRequestDispatcher("profilecustomer.jsp").forward(request, response);
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
        try {
            boolean lock = true;
            String type = request.getParameter("type");
            String companyName = request.getParameter("name");
            DAOSuppliers daoSuppliers = new DAOSuppliers();
            DAOShippers daoShippers = new DAOShippers();
            DAOCustomers daoCustomers = new DAOCustomers();
            String check = request.getParameter("lock");
            String newName = request.getParameter("compName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            if (check.equals("ON")) {
                lock = false;
            }
            if (type.equals("sup")) {
                Suppliers entity = daoSuppliers.GetSupplierByCompanyName(companyName);
                String page = request.getParameter("page");
                if (Boolean.compare(entity.isStatus(), lock) != 0) {
                    entity.setStatus(lock);
                    entity.setCompanyName(newName);
                    entity.setPhone(phone);
                    entity.setEmail(email);
                    entity.setHomePage(page);
                    daoSuppliers.AccountSupplier(entity);
                    if (!entity.isStatus()) {
                        request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                    }
                }
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                response.sendRedirect("profile?type="+type+"&id="+entity.getSupplierID());
            }
            if (type.equals("ship")) {
                Shippers entity = daoShippers.getShipperByCompanyName(companyName);
                if (Boolean.compare(entity.isStatus(), lock) != 0) {
                    entity.setStatus(lock);
                    entity.setCompanyName(newName);
                    entity.setEmail(email);
                    entity.setPhone(phone);
                    daoShippers.AccountShipper(entity);
                    if (!entity.isStatus()) {
                        request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                    }
                }
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                response.sendRedirect("profile?type="+type+"&id="+entity.getShipperID());
            }
            if (type.equals("customer")) {
                String id_raw = request.getParameter("customerID");
                int id = Integer.parseInt(id_raw);
                Customers entity = daoCustomers.getCustomerByCustomerID(id);
                if (Boolean.compare(entity.getAcc().isStatus(), lock) != 0) {
                    entity.getAcc().setStatus(lock);
                    daoCustomers.LockCustomers(entity);
                    if (!entity.getAcc().isStatus()) {
                        request.setAttribute("msg", "Tài khoản này đã bị khoá hoặc dừng hoạt động!");
                    }
                }
                request.setAttribute("entity", entity);
                request.setAttribute("type", type);
                response.sendRedirect("profile?type="+type+"&id="+entity.getCustomerID());
            }
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
