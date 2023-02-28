/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Categories;
import entity.Products;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategories;
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
public class ListAllProductsServlet extends HttpServlet {

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
            out.println("<title>Servlet ListAllProductsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListAllProductsServlet at " + request.getContextPath() + "</h1>");
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
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        
        PrintWriter out = response.getWriter();
        
        Vector<Suppliers> listAllSuppliers = daoSuppliers.getAllSuppliers();

        String discontinued_raw = request.getParameter("discontinued");
        String category = request.getParameter("categoryID");
        String[] sid_raw = request.getParameterValues("supplierID");
        String orderby_raw = request.getParameter("orderby");
        int[] sid = null;
        if (sid_raw != null) {
            sid = new int[sid_raw.length];
            for (int i = 0; i < sid.length; i++) {
                sid[i] = Integer.parseInt(sid_raw[i]);
            }
        }
        
        Vector<Products> vector = daoProducts.getProductsBySuppliersByAdmin(sid, category, orderby_raw, discontinued_raw);
        boolean[] cid = new boolean[listAllSuppliers.size()];
        for (int i = 0; i < cid.length; i++) {
            cid[i] = isCheck(listAllSuppliers.get(i).getSupplierID(), sid);
        }
        request.setAttribute("cid", cid);
        request.setAttribute("category", category);
        request.setAttribute("orderby_raw", orderby_raw);
        request.setAttribute("discontinued_raw", discontinued_raw);
        
        Vector<Categories> listAllCategories = daoCategories.getAllCategories();
        
        request.setAttribute("listAllCategories", listAllCategories);
        request.setAttribute("listAllSuppliers", listAllSuppliers);
        request.setAttribute("listAllProducts", vector);
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    
    private boolean isCheck(int d, int[] id) {
        if (id == null) {
            return false;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == d) {
                    return true;
                }
            }
            return false;
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
        DAOProducts daoProducts = new DAOProducts();
        String pid_raw = request.getParameter("pid");
        try {
            int pid = Integer.parseInt(pid_raw);
            Products product = daoProducts.getProductByID(pid);
            if (product == null) {
                request.setAttribute("error", "Không tìm thấy! Vui lòng kiểm tra lại.");
            }
            request.setAttribute("product", product);
            request.getRequestDispatcher("searchproduct.jsp").forward(request, response);
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
