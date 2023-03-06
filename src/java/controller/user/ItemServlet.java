/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Cart;
import entity.Categories;
import entity.Customers;
import entity.ProductImage;
import entity.ProductInfo;
import entity.Products;
import entity.Review;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCategories;
import model.DAOProductImage;
import model.DAOProductInfo;
import model.DAOProducts;
import model.DAOReview;
import model.DAOSuppliers;

/**
 *
 * @author daova
 */
public class ItemServlet extends HttpServlet {

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
            out.println("<title>Servlet ItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemServlet at " + request.getContextPath() + "</h1>");
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
        DAOProductImage daoProductImage = new DAOProductImage();
        DAOProducts daoProducts = new DAOProducts();
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        DAOReview daoReview = new DAOReview();
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

        try {
            HttpSession session = request.getSession();
            Customers cus = (Customers) session.getAttribute("account");

            String pid_raw = request.getParameter("pid");
            int productID = Integer.parseInt(pid_raw);
            Vector<ProductImage> getAllImageByProductID = daoProductImage.getAllImageProductByProductID(productID);
            Products pro = daoProducts.getProductByID(productID);
            Categories cate = daoCategories.getCategoryNameByProductID(productID);
            ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
            Vector<Review> getAllReview = daoReview.getAllReviewByProductID(productID);
            int totalReview = daoReview.TotalReviewByUser(productID);
            if (cus != null) {
                Vector<Review> getReviewByAccount = daoReview.getAllReviewByProductIDAndAccount(productID, cus.getAcc().getId());
                request.setAttribute("getReviewByAccount", getReviewByAccount);
            }
            Vector<Integer> numberReview = daoReview.numberReviewtarProductID(productID);
            Vector<Products> getAllProductsSame = daoProducts.getAllProductsSame(pro.getSupplier().getSupplierID(), pro.getCategory().getCategoryID(), productID);
            double rateAvg = daoReview.getAverageRate(productID);
            Suppliers sup = daoSuppliers.getSupplierByProductID(productID);
            if (cate.getCategoryName().equalsIgnoreCase("SmartPhone")) {
                request.setAttribute("type", "mobile");
            }
            if (cate.getCategoryName().equalsIgnoreCase("Laptop")) {
                request.setAttribute("type", "laptop");
            }
            if (cate.getCategoryName().equalsIgnoreCase("Tablet")) {
                request.setAttribute("type", "tablet");
            }

            request.setAttribute("titlePage", pro.getProductName());
            request.setAttribute("sup", sup);
            request.setAttribute("totalReview", totalReview);

            request.setAttribute("rateAvg", rateAvg);
            request.setAttribute("getAllProductsSame", getAllProductsSame);
            request.setAttribute("numberReview", numberReview);
            request.setAttribute("getAllReview", getAllReview);
            request.setAttribute("proInfo", proInfo);
            request.setAttribute("cate", cate);
            request.setAttribute("pro", pro);
            request.setAttribute("getAllImageByProductID", getAllImageByProductID);
        } catch (Exception e) {
        }

        request.getRequestDispatcher("item.jsp").forward(request, response);
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
