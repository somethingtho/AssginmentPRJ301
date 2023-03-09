/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Categories;
import entity.ProductImage;
import entity.Products;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import model.DAOCategories;
import model.DAOProductImage;
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(maxFileSize = 16177215)
public class UpdateProductServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath() + "</h1>");
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
        String productID_raw = request.getParameter("pid");
        DAOProducts daoProducts = new DAOProducts();
        DAOSuppliers daoSupplier = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductImage daoProductImage = new DAOProductImage();
        String id_raw = request.getParameter("imgid");
        DAOProductImage daoProImg = new DAOProductImage();
        try {
            int productID = Integer.parseInt(productID_raw);

            Products product = daoProducts.getProductByID(productID);
            Vector<Categories> listAllCate = daoCategories.getAllCategories();
            Vector<Suppliers> listAllSup = daoSupplier.getAllSuppliers();
            Vector<ProductImage> listAllImage = daoProductImage.getAllImageProductByProductID(productID);

            if (id_raw != null) {
                int id = Integer.parseInt(id_raw);
                for (int i = 0; i < listAllImage.size(); i++) {
                    if (listAllImage.get(i).getId() == id) {
                        listAllImage.remove(i);
                        daoProImg.DeleteProductImg(id);
                    }
                }

            }
            listAllImage = daoProductImage.getAllImageProductByProductID(productID);
            request.setAttribute("product", product);
            request.setAttribute("listAllImage", listAllImage);
            request.setAttribute("listAllCate", listAllCate);
            request.setAttribute("listAllSup", listAllSup);

            request.getRequestDispatcher("confirmproduct.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        DAOProducts daoProducts = new DAOProducts();
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOSuppliers daoSupplier = new DAOSuppliers();
        DAOProductImage daoProductImage = new DAOProductImage();
        try {
            String productIDOld = request.getParameter("pid");
            String productName = request.getParameter("productName");
            String categoryID_raw = request.getParameter("categoryID");
            String supplierID_raw = request.getParameter("supplierID");
            String price_raw = request.getParameter("price");
            String stock_raw = request.getParameter("stock");
            String discount_raw = request.getParameter("discount");
            String discontinued = request.getParameter("discontinued");
            boolean dis;
            if (discontinued.equals("ON")) {
                dis = false;
            } else {
                dis = true;
            }
            Part filePart = request.getPart("photo");

            String size = request.getParameter("size");
            String weight = request.getParameter("weight");
            String substance = request.getParameter("substance");
            String cpu = request.getParameter("cpu");
            String screen = request.getParameter("screen");
            String ram = request.getParameter("ram");
            String camera = request.getParameter("camera");
            String graphicsCard = request.getParameter("card");
            String hardDrive = request.getParameter("hdd");
            String os = request.getParameter("os");
            String batteryCapacity = request.getParameter("pin");

            List<Part> fileParts = (List<Part>) request.getParts();

            int productID = Integer.parseInt(productIDOld);
            String price_rep = price_raw.replace(".", "");
            int categoryID = Integer.parseInt(categoryID_raw);
            int supplierID = Integer.parseInt(supplierID_raw);
            double price = Double.parseDouble(price_rep);
            int stock = Integer.parseInt(stock_raw);
            double discount = Double.parseDouble(discount_raw)/100;

            Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
            Categories category = daoCategories.getCategoryByCategoryID(categoryID);
            Products product = daoProducts.getProductByID(productID);
            product.setProductName(productName);
            product.setCategory(category);
            product.setDiscontinued(dis);
            product.getProInfo().setBatteryCapacity(batteryCapacity);
            product.getProInfo().setCamera(camera);
            product.getProInfo().setCpu(cpu);
            product.getProInfo().setGraphicsCard(graphicsCard);
            product.getProInfo().setHardDrive(hardDrive);
            product.getProInfo().setOs(os);
            product.getProInfo().setRam(ram);
            product.getProInfo().setScreen(screen);
            product.getProInfo().setSize(size);
            product.getProInfo().setSubstance(substance);
            product.getProInfo().setWeight(weight);
            product.setSupplier(supplier);
            product.setUnitPrice(price);
            product.setUnitsInStock(stock);
            product.setDiscount(discount);

            int n  = daoProducts.UpdateProduct(product, fileParts, filePart);
            if(n  >1){
                request.setAttribute("msg", "Cập nhật thành công!!");
            }
            Vector<Categories> listAllCate = daoCategories.getAllCategories();
            Vector<Suppliers> listAllSup = daoSupplier.getAllSuppliers();
            Vector<ProductImage> listAllImage = daoProductImage.getAllImageProductByProductID(productID);

            request.setAttribute("listAllImage", listAllImage);
            request.setAttribute("listAllCate", listAllCate);
            request.setAttribute("listAllSup", listAllSup);
            product = daoProducts.getProductByID(productID);
            request.setAttribute("product", product);
            request.getRequestDispatcher("confirmproduct.jsp").forward(request, response);
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

    private boolean isImageFile(String filePath) {
        // Get the file extension
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1);

        // Check if the file extension is an image file extension
        return extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("gif")
                || extension.equalsIgnoreCase("bmp");
    }

    public String getFileName(Part part) {
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

}
