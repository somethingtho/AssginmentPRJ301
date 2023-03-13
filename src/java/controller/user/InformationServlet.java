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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.Collection;
import java.util.Vector;
import model.DAOCustomers;
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author daova
 */
@MultipartConfig(maxFileSize = 16177215)
public class InformationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet InformationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InformationServlet at " + request.getContextPath() + "</h1>");
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

        Vector<Products> listAllProducts = daoProducts.getAllProducts();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, listAllProducts);
        request.setAttribute("cart", cart);
        request.setAttribute("size", cart.getItems().size());

        Vector<Suppliers> listAllSuppliersSmartPhone = daoSuppliers.getAllSuppliersSmartPhone();
        Vector<Suppliers> listAllSuppliersLaptop = daoSuppliers.getAllSuppliersLaptop();
        Vector<Suppliers> listAllSuppliersTablet = daoSuppliers.getAllSuppliersTablet();

        request.setAttribute("listAllSuppliersSmartPhone", listAllSuppliersSmartPhone);
        request.setAttribute("listAllSuppliersLaptop", listAllSuppliersLaptop);
        request.setAttribute("listAllSuppliersTablet", listAllSuppliersTablet);
        request.getRequestDispatcher("information.jsp").forward(request, response);
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
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        PrintWriter out = response.getWriter();
        Vector<Products> listAllProducts = daoProducts.getAllProducts();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, listAllProducts);
        request.setAttribute("cart", cart);
        request.setAttribute("size", cart.getItems().size());

        Vector<Suppliers> listAllSuppliersSmartPhone = daoSuppliers.getAllSuppliersSmartPhone();
        Vector<Suppliers> listAllSuppliersLaptop = daoSuppliers.getAllSuppliersLaptop();
        Vector<Suppliers> listAllSuppliersTablet = daoSuppliers.getAllSuppliersTablet();

        DAOCustomers daoCustomers = new DAOCustomers();
        InputStream inputStream = null;
        HttpSession session = request.getSession();

        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            String fileName = extractFileName(part);
            if (fileName != null && !fileName.isEmpty() && isImageFile(fileName)) {
                inputStream = part.getInputStream();
                out.print(fileName);
                out.print(isImageFile(fileName));
            }
        }

        Customers cus = (Customers) session.getAttribute("account");
        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender_raw = request.getParameter("gender");
        String email = request.getParameter("email");
        boolean gender;
        request.setAttribute("listAllSuppliersSmartPhone", listAllSuppliersSmartPhone);
        request.setAttribute("listAllSuppliersLaptop", listAllSuppliersLaptop);
        request.setAttribute("listAllSuppliersTablet", listAllSuppliersTablet);
        Customers check = daoCustomers.getCustomerByEmail(email);
        Customers check2 = daoCustomers.getCustomerByPhone(phone);
        if (check != null) {
            if (!check.getAcc().getUserName().equalsIgnoreCase(cus.getAcc().getUserName())) {
                request.setAttribute("error", "Email đã được sử dụng vui lòng chọn Email khác!");
                request.getRequestDispatcher("information.jsp").forward(request, response);
            }

        }

        if (check2 != null) {
            if (!check2.getAcc().getUserName().equalsIgnoreCase(cus.getAcc().getUserName())) {
                request.setAttribute("error", "Số điện thoại đã được sử dụng vui lòng chọn Số điện thoại khác!");
                request.getRequestDispatcher("information.jsp").forward(request, response);
            }
        }
        try {
            gender = gender_raw.equals("male");
            cus.setPhone(phone);
            cus.setAddress(address);
            cus.setCustomerName(customerName);
            cus.setEmail(email);
            cus.setGender(gender);
            daoCustomers.UpdateCustomers(cus, inputStream);
            Customers update = daoCustomers.getCustomerByUserName(cus.getAcc().getUserName());
            cus.setBase64Image(update.getBase64Image());
            session.setAttribute("account", cus);
            request.setAttribute("error", "Cập nhật thành công!");
            request.getRequestDispatcher("information.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            String[] tokens = contentDisp.split(";");
            for (String token : tokens) {
                if (token.startsWith("filename")) {
                    return token.substring(token.indexOf("=") + 2, token.length() - 1);
                }
            }
        }
        return null;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }

    private boolean isImageFile(String filePath) {
        // Get the file extension
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1);

        // Check if the file extension is an image file extension
        return extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("jfif")
                || extension.equalsIgnoreCase("webp")
                || extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("gif")
                || extension.equalsIgnoreCase("bmp");
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
