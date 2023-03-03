/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Cart;
import entity.Products;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author daova
 */
public class MobileServlet extends HttpServlet {

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
            out.println("<title>Servlet MobileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MobileServlet at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("titlePage", "SmartPhone");
        Vector<Suppliers> listAllSuppliersSmartPhone = daoSuppliers.getAllSuppliersSmartPhone();
        Vector<Suppliers> listAllSuppliersLaptop = daoSuppliers.getAllSuppliersLaptop();
        Vector<Suppliers> listAllSuppliersTablet = daoSuppliers.getAllSuppliersTablet();

        request.setAttribute("listAllSuppliersSmartPhone", listAllSuppliersSmartPhone);
        request.setAttribute("listAllSuppliersLaptop", listAllSuppliersLaptop);
        request.setAttribute("listAllSuppliersTablet", listAllSuppliersTablet);
        double maxPrice = daoProducts.getMaxPrice();
        request.setAttribute("maxPrice", maxPrice);
        double minPrice = daoProducts.getMinPrice();
        request.setAttribute("minPrice", minPrice);
        Vector<Products> listAllType = daoProducts.getAllMobile();
        String type = "mobile";
        Vector<Suppliers> listAllSuppliersType = daoSuppliers.getAllSuppliersSmartPhone();
        String[] sid_raw = request.getParameterValues("sid");
        int[] sid = null;
        if (sid_raw != null) {
            sid = new int[sid_raw.length];
            for (int i = 0; i < sid.length; i++) {
                sid[i] = Integer.parseInt(sid_raw[i]);
            }
        }
        String orderby_raw = request.getParameter("orderby");

        if (orderby_raw == null) {
            orderby_raw = "1";
        }
        String from_raw = request.getParameter("from");
        String to_raw = request.getParameter("to");
        double from = minPrice, to = maxPrice;

        if (from_raw != null && to_raw != null) {
            from = Double.parseDouble(from_raw);
            to = Double.parseDouble(to_raw);
        }
        request.setAttribute("to", to);
        request.setAttribute("from", from);
        Vector<Products> vector = daoProducts.getProductsBySuppliers(sid, "SmartPhone", orderby_raw, from, to);
        String link = "";
        boolean[] cid = new boolean[listAllSuppliersType.size()];
        for (int i = 0; i < cid.length; i++) {
            if (isCheck(listAllSuppliersType.get(i).getSupplierID(), sid)) {
                cid[i] = true;
                link = link + "&sid=" + listAllSuppliersType.get(i).getSupplierID();
            } else {
                cid[i] = false;
            }
        }
        
        if(!link.isEmpty()) link = link + "&";
        request.setAttribute("cid", cid);
        request.setAttribute("type", type);
        request.setAttribute("listAllSuppliersType", listAllSuppliersType);
        int numPs = vector.size();
        int numperPage = 8;
        int numpage = numPs / numperPage + (numPs % numperPage == 0 ? 0 : 1);
        int start, end;
        String tpage = request.getParameter("page");
        int page;

        try {
            page = Integer.parseInt(tpage);
        } catch (NumberFormatException e) {
            page = 1;
        }
        start = (page - 1) * numperPage;
        if (page * numperPage > numPs) {
            end = numPs;
        } else {
            end = page * numperPage;
        }
        
        Vector<Products> list = daoProducts.getAllProducts();
        
        Cookie[] arr=request.getCookies();
        String txt="";
        if(arr!=null){
            for(Cookie o:arr){
                if(o.getName().equals("cart")){
                    txt+=o.getValue();
                }
            }
        }
        Cart cart=new Cart(txt, list);
        request.setAttribute("cart", cart);
        request.setAttribute("size", cart.getItems().size());
        
        request.setAttribute("link", link);
        request.setAttribute("cate", "SmartPhone");
        Vector<Products> vector1 = daoProducts.getListByPage(vector, start, end);
        request.setAttribute("listAllType", vector1);
        request.setAttribute("page", page);
        request.setAttribute("num", numpage);
        request.setAttribute("orderby", orderby_raw);
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
