/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import entity.ProductInfo;
import entity.Products;
import entity.Review;
import entity.Suppliers;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daova
 */
public class DAOProducts extends DBContext {

    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
//        int[] id = {1};
//        String cateID = "1";
//        String idorder = "ProductID1";
//        String idDiscontinued = null;
//        Vector<Products> list = dao.getNextProducts(id, cateID, idorder, idDiscontinued, 0);
//        for (Products products : list) {
//            System.out.println(products);
//        }

        Vector<Products> list;
        try {
            list = dao.getReviewsProductsByAdmin();
            for (Products products : list) {
                System.out.println(products.getReviews());
            }
        } catch (IOException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * It gets the top 5 products with the highest UnitsOnOrder from the
     * database and returns them as a vector
     *
     * @param id the id of the supplier
     * @return A vector of Products
     */
    public Vector<Products> getProductBestSaleBySupplier(int id) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM dbo.Products\n"
                + "WHERE SupplierID =?\n"
                + "ORDER BY UnitsOnOrder DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It returns the number of products that a supplier has
     *
     * @param sid Supplier ID
     * @return The number of products by a supplier.
     */
    public int TotalProductsBySupplier(int sid) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Products WHERE SupplierID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * I want to insert a new product into the database, and then insert the
     * product's information and images into the database
     *
     * @param product a product object
     * @param input List of Part
     * @param filePart Part
     * @return The number of rows affected by the SQL statement.
     */
    public int AddNewProduct(Products product, List<Part> input, Part filePart) throws IOException {
        int number = 0;
        DAOProductImage daoImg = new DAOProductImage();
        DAOProducts daoProducts = new DAOProducts();
        DAOProductInfo daoInfo = new DAOProductInfo();
        String sql = "INSERT INTO dbo.Products(ProductName, SupplierID, CategoryID, UnitPrice, UnitsInStock, UnitsOnOrder, Discount,Discontinued, Image) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getSupplier().getSupplierID());
            pre.setInt(3, product.getCategory().getCategoryID());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getUnitsInStock());
            pre.setInt(6, product.getUnitsOnOrder());
            pre.setDouble(7, product.getDiscount());
            pre.setBoolean(8, product.isDiscontinued());
            if (filePart != null && !getFileName(filePart).isEmpty()) {
                pre.setBinaryStream(9, filePart.getInputStream());
            } else {
                pre.setBinaryStream(9, null);
            }
            number = pre.executeUpdate();

            Products proNew = daoProducts.getProductNew();
            ProductInfo proInfo = product.getProInfo();
            proInfo.setProductID(proNew.getProductID());
            number += daoInfo.InsertProductInfo(proInfo);
//            System.out.println(proNew);
            number += daoImg.AddImg(input, proNew.getProductID());
        } catch (SQLException ex) {
        }

        return number;
    }

    /**
     * It gets the last product added to the database and returns it as a
     * Products object
     *
     * @return A product object.
     */
    public Products getProductNew() {
        Products product = null;

        String sql = "SELECT TOP 1 * FROM Products ORDER BY ProductID DESC";
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    try {
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] imageBytes = outputStream.toByteArray();
                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                        inputStream.close();
                        outputStream.close();
                        Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                        Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                        product = new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String base64Image = null;
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    product = new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return product;

    }

    /**
     * I want to update the product table and the productinfo table at the same
     * time
     *
     * @param product a Product object
     * @param input List of Part
     * @param filePart the image file
     * @return The number of rows affected by the update.
     */
    public int UpdateProduct(Products product, List<Part> input, Part filePart) throws IOException {
        int number = 0;

        String sql = "UPDATE Products SET ProductName = ?, SupplierID = ?, CategoryID = ?, UnitPrice = ?, UnitsInStock = ?, Discount = ?,Discontinued = ? WHERE ProductID = ?";
        if (filePart != null && !getFileName(filePart).isEmpty()) {
            sql = "UPDATE Products SET ProductName = ?, SupplierID = ?, CategoryID = ?, UnitPrice = ?, UnitsInStock = ?, Discount = ?, Discontinued = ?, Image = ? WHERE ProductID = ?";
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getSupplier().getSupplierID());
            pre.setInt(3, product.getCategory().getCategoryID());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getUnitsInStock());
            pre.setDouble(6, product.getDiscount());
            pre.setBoolean(7, product.isDiscontinued());
            if (filePart != null && !getFileName(filePart).isEmpty()) {
                pre.setBinaryStream(8, filePart.getInputStream());
                pre.setInt(9, product.getProductID());
            } else {
                pre.setInt(8, product.getProductID());
            }
            number = pre.executeUpdate();

            DAOProductImage daoProImg = new DAOProductImage();
            DAOProductInfo daoProInfo = new DAOProductInfo();
            daoProInfo.UpdateProductInfo(product.getProInfo());
            number += daoProImg.AddImg(input, product.getProductID());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    /**
     * It returns the filename of the file that was uploaded
     *
     * @param part The part of the request that contains the file
     * @return The file name of the uploaded file.
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            String[] tokens = contentDisp.split(";");
            for (String token : tokens) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf("=") + 2, token.length() - 1);
                }
            }
        }
        return null;
    }

    /**
     * It gets all the products from the database and returns them as a vector
     *
     * @return A vector of Products
     */
    public Vector<Products> getAllProductsByAdmin() throws IOException {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 8 * FROM Products ORDER BY ProductID ASC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets the first two products from the database and returns them as a vector
     * 
     * @return A vector of Products
     */
    public Vector<Products> getReviewsProductsByAdmin() throws IOException {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        DAOReview daoReview = new DAOReview();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 2 * FROM Products ORDER BY ProductID ASC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    Vector<Review> reviews = daoReview.getAllReviewByProductIDAndAdmin(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo, reviews));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It returns the number of products in the database
     *
     * @return The number of products in the database.
     */
    public int TotalProducts() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Products";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * It gets all the products from the database and returns them as a vector
     *
     * @return A vector of Products objects.
     */
    public Vector<Products> getAllProducts() throws IOException {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products WHERE Discontinued = 0";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public Vector<Products> getProductByDiscount() throws IOException {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 8 * FROM Products WHERE Discontinued = 0 ORDER BY Discount DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets all the products from the database and returns a vector of
     * products
     *
     * @return A vector of Products
     */
    public Vector<Products> getAllProductsWithInfo() throws IOException {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets the top 4 products from the database that are laptops and orders
     * them by the amount of units on order
     *
     * @return A vector of Products objects.
     */
    public Vector<Products> getLaptopHotSale() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 4 * FROM Products WHERE Discontinued  = 0 AND CategoryID = "
                + "(SELECT CategoryID FROM dbo.Categories WHERE CategoryName LIKE '%LAPTOP%') ORDER BY UnitsOnOrder DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets the top 4 products from the database that are not discontinued
     * and are in the category of Smartphone, and then it orders them by the
     * number of units on order
     *
     * @return A vector of Products objects.
     */
    public Vector<Products> getSmartPhoneHotSale() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 4 * FROM Products WHERE Discontinued = 0 AND CategoryID = "
                + "(SELECT CategoryID FROM dbo.Categories WHERE CategoryName LIKE '%Smartphone%') ORDER BY UnitsOnOrder DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets the top 4 products that are not discontinued and are in the
     * category of Tablet, and then it orders them by the number of units on
     * order
     *
     * @return A vector of Products
     */
    public Vector<Products> getTabletHotSale() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 4 * FROM Products WHERE Discontinued = 0 AND CategoryID = "
                + "(SELECT CategoryID FROM dbo.Categories WHERE CategoryName LIKE '%Tablet%') ORDER BY UnitsOnOrder DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets all the products from the database and returns a vector of
     * products
     *
     * @return A vector of Products objects.
     */
    public Vector<Products> getAllLaptop() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Products WHERE Discontinued = 0 AND CategoryID = (SELECT CategoryID FROM dbo.Categories WHERE CategoryName = 'Laptop')";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets all the products from the database that are not discontinued and
     * are in the category of Tablet
     *
     * @return A vector of Products objects.
     */
    public Vector<Products> getAllTablet() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Products WHERE Discontinued = 0 AND CategoryID = (SELECT CategoryID FROM dbo.Categories WHERE CategoryName = 'Tablet')";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets all the products from the database and returns them as a vector
     *
     * @return A vector of Products objects.
     */
    public Vector<Products> getAllMobile() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Products WHERE Discontinued = 0 AND CategoryID = (SELECT CategoryID FROM dbo.Categories WHERE CategoryName = 'SmartPhone')";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It takes a vector of products, a start index, and an end index, and
     * returns a vector of products
     *
     * @param vector the vector of products
     * @param start the index of the first element in the vector to be returned
     * @param end the end index of the vector
     * @return A vector of products.
     */
    public Vector<Products> getListByPage(Vector<Products> vector,
            int start, int end) {
        Vector<Products> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(vector.get(i));
        }
        return arr;
    }

    /**
     * It gets products by suppliers, category name, and order
     *
     * @param id an array of supplier IDs
     * @param categoryName the name of the category
     * @param idorder 1 = ProductName, 2 = UnitPrice DESC, 3 = UnitPrice ASC
     * @return A vector of Products
     */
    public Vector<Products> getProductsBySuppliers(int[] id, String categoryName, String idorder) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE Discontinued  = 0 AND CategoryID =  (SELECT CategoryID FROM Categories WHERE CategoryName = ?)";
        if (id != null) {
            sql += "AND p.SupplierID IN(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ") ";
        }
        String orderby = "ProductName";
        if (idorder == null || idorder.equals("1")) {
            orderby = "ProductName";
        }
        if (idorder.equals("2")) {
            orderby = "UnitPrice DESC";
        }
        if (idorder.equals("3")) {
            orderby = "UnitPrice ASC";
        }

        sql = sql + "ORDER BY " + orderby;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, categoryName);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets the product name from the database and converts it to base64
     *
     * @param name the name of the product
     * @return A vector of Products
     */
    public Vector<Products> SearchByNames(String name) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE Discontinued = 0 AND p.ProductName like ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    //Add Products
    /*
    public int AddProducts(Products product, InputStream file) {
        int number = 0;
        String sql = "INSERT INTO dbo.Products(ProductName, SupplierID, CategoryID, UnitPrice, UnitsInStock, UnitsOnOrder, Discontinued, Image) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getSupplierID());
            pre.setInt(3, product.getCategoryID());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getUnitsInStock());
            pre.setInt(6, product.getUnitsOnOrder());
            pre.setBoolean(7, product.isDiscontinued());
            if (file != null) {
                pre.setBlob(8, file);
            }
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    //Delete  Products
    public int DeleteShippers(int id) {
        int number = 0;
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    //Update Products
    public int UpdateProducts(Products product) {
        int number = 0;
        String sql = "UPDATE Products SET ProductName = ?, SupplierID = ?, CategoryID = ?, UnitPrice =?, UnitsInStock = ?, UnitsOnOrder = ?, Discontinued = ? WHERE ProductID = ?";
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getSupplierID());
            pre.setInt(3, product.getCategoryID());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getUnitsInStock());
            pre.setInt(6, product.getUnitsOnOrder());
            pre.setBoolean(7, product.isDiscontinued());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
     */
    /**
     * It gets the maximum price of a product from the database, multiplies it
     * by 1.2, and returns the result
     *
     * @return The max price of the products in the database.
     */
    public double getMaxPrice() {
        int maxPrice = 0;
        String sql = "SELECT TOP 1 UnitPrice FROM Products Order by Unitprice DESC";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                maxPrice = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maxPrice * 1.2;
    }

    /**
     * This function returns the minimum price of all products in the database
     *
     * @return The minimum price of the products in the database.
     */
    public double getMinPrice() {
        int minPrice = 0;
        String sql = "SELECT TOP 1 UnitPrice FROM Products Order by Unitprice ASC";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                minPrice = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return minPrice;
    }

    /**
     * It gets products by suppliers, category name, idorder, from and to
     *
     * @param id an array of supplier IDs
     * @param categoryName String
     * @param idorder 1 = ProductName, 2 = UnitPrice DESC, 3 = UnitPrice ASC
     * @param from the minimum price
     * @param to the maximum price
     * @return A vector of products.
     */
    public Vector<Products> getProductsBySuppliers(int[] id, String categoryName, String idorder, double from, double to) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE Discontinued = 0 AND CategoryID =  (SELECT CategoryID FROM Categories WHERE CategoryName = ?)";
        if (id != null) {
            sql += "AND p.SupplierID IN(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ") ";
        }
        if (from >= 0 || to <= getMaxPrice()) {
            sql = sql + "AND UnitPrice BETWEEN " + from + " AND " + to;
        }
        String orderby = "ProductName";
        if (idorder == null || idorder.equals("1")) {
            orderby = "ProductName";
        }
        if (idorder.equals("2")) {
            orderby = "UnitPrice DESC";
        }
        if (idorder.equals("3")) {
            orderby = "UnitPrice ASC";
        }

        sql = sql + " ORDER BY " + orderby;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, categoryName);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It searches for products by name, price range, and order by
     *
     * @param name the name of the product
     * @param idorder 1 = ProductName, 2 = UnitPrice DESC, 3 = UnitPrice ASC
     * @param from the minimum price
     * @param to the maximum price
     * @return A vector of Products
     */
    public Vector<Products> SearchByNames(String name, String idorder, double from, double to) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE Discontinued= 0 AND p.ProductName like ? ";
        if (from >= 0 || to <= getMaxPrice()) {
            sql = sql + "AND UnitPrice BETWEEN " + from + " AND " + to;
        }
        String orderby = "ProductName";
        if (idorder == null || idorder.equals("1")) {
            orderby = "ProductName";
        }
        if (idorder.equals("2")) {
            orderby = "UnitPrice DESC";
        }
        if (idorder.equals("3")) {
            orderby = "UnitPrice ASC";
        }
        sql = sql + " ORDER BY " + orderby;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /**
     * It gets a product by its ID, and returns it as a Products object
     *
     * @param pID the product ID
     * @return A Products object.
     */
    public Products getProductByID(int pID) {
        DAOReview daoReview = new DAOReview();
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        String sql = "SELECT * FROM dbo.Products WHERE ProductID = ?";
        Products p = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    p = new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    /**
     * I want to get all products that have the same supplierID and categoryID
     * and productID is not equal to the productID that I passed in
     *
     * @param supid SupplierID
     * @param cateid category id
     * @param pid product id
     * @return A vector of Products objects.
     */
    public Vector<Products> getAllProductsSame(int supid, int cateid, int pid) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOp 4 * FROM dbo.Products WHERE Discontinued = 0 AND SupplierID  = ? AND CategoryID = ? AND ProductID <> ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supid);
            pre.setInt(2, cateid);
            pre.setInt(3, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * I want to get all products from database, but I want to get only products
     * that have supplierID in the array of supplierID.
     *
     * @param id an array of supplier IDs
     * @param cateID CategoryID
     * @param idorder the order by column
     * @param idDiscontinued ON or OFF
     * @return A Vector of Products
     */
    public Vector<Products> getProductsBySuppliersByAdmin(int[] id, String cateID, String idorder, String idDiscontinued) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 8 Products.* FROM Products INNER JOIN dbo.ProductInfo ON Products.ProductID = ProductInfo.ProductID WHERE 1 = 1 ";
        if (id != null && id.length > 0) {
            sql += "AND Products.SupplierID IN(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ") ";
        }
        String orderby = " ProductName ";
        if (idorder == null || idorder.equalsIgnoreCase("select") || idorder.equals("ProductName")) {
            orderby = " ProductName ";
        }
        if (cateID != null && !cateID.equalsIgnoreCase("Select")) {
            sql = sql + " AND CategoryID = " + cateID;
        }
        if (idDiscontinued != null && idDiscontinued.equalsIgnoreCase("ON")) {
            sql = sql + " AND Discontinued = 1 ";
        }
        if (idDiscontinued != null && idDiscontinued.equalsIgnoreCase("OFF")) {
            sql = sql + " AND Discontinued = 0 ";
        }

        if (idorder != null && idorder.equals("ProductID1")) {
            orderby = " Products.ProductID ASC ";
        }
        if (idorder != null && idorder.equals("ProductID2")) {
            orderby = " Products.ProductID DESC ";
        }

        if (idorder != null && idorder.equals("UnitPrice1")) {
            orderby = " UnitPrice ASC ";
        }
        if (idorder != null && idorder.equals("UnitPrice2")) {
            orderby = " UnitPrice DESC ";
        }

        if (idorder != null && idorder.equals("DateCreated1")) {
            orderby = " DateCreated ASC ";
        }

        if (idorder != null && idorder.equals("DateCreated2")) {
            orderby = "DateCreated DESC";
        }

        if (idorder != null && idorder.equals("UnitsOnOrder1")) {
            orderby = "UnitsOnOrder ASC";
        }

        if (idorder != null && idorder.equals("UnitsOnOrder2")) {
            orderby = "UnitsOnOrder DESC";
        }

        if (idorder != null && idorder.equals("UnitsInStock1")) {
            orderby = "UnitsInStock ASC";
        }

        if (idorder != null && idorder.equals("UnitsInStock2")) {
            orderby = "UnitsInStock DESC";
        }

        sql = sql + " ORDER BY " + orderby;

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    /**
     * It gets the next 4 products from the database based on the parameters
     * passed in
     *
     * @param id an array of supplier IDs
     * @param cateID Category ID
     * @param idorder the order by which the products are sorted
     * @param idDiscontinued ON/OFF
     * @param amount the amount of products that have been loaded
     * @return A Vector of Products
     */
    public Vector<Products> getNextProducts(int[] id, String cateID, String idorder, String idDiscontinued, int amount) {
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT Products.* FROM Products INNER JOIN ProductInfo ON Products.ProductID = ProductInfo.ProductID WHERE 1 = 1 ";
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();

        if (id != null && id.length > 0) {
            sql += "AND Products.SupplierID IN(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ") ";
        }
        String orderby = "ProductName";
        if (idorder == null || idorder.equalsIgnoreCase("select") || idorder.equals("ProductName")) {
            orderby = "ProductName";
        }
        if (cateID != null && !cateID.equalsIgnoreCase("Select")) {
            sql = sql + "AND CategoryID = " + cateID;
        }
        if (idDiscontinued != null && idDiscontinued.equalsIgnoreCase("ON")) {
            sql = sql + "AND Discontinued = 1 ";
        }
        if (idDiscontinued != null && idDiscontinued.equalsIgnoreCase("OFF")) {
            sql = sql + "AND Discontinued = 0 ";
        }

        if (idorder != null && idorder.equals("ProductID1")) {
            orderby = " Products.ProductID ASC ";
        }
        if (idorder != null && idorder.equals("ProductID2")) {
            orderby = " Products.ProductID DESC ";
        }

        if (idorder != null && idorder.equals("UnitPrice1")) {
            orderby = "UnitPrice ASC";
        }
        if (idorder != null && idorder.equals("UnitPrice2")) {
            orderby = "UnitPrice DESC";
        }

        if (idorder != null && idorder.equals("DateCreated1")) {
            orderby = "UnitPrice ASC";
        }

        if (idorder != null && idorder.equals("DateCreated2")) {
            orderby = "DateCreated DESC";
        }

        if (idorder != null && idorder.equals("UnitsOnOrder1")) {
            orderby = "UnitsOnOrder ASC";
        }

        if (idorder != null && idorder.equals("UnitsOnOrder2")) {
            orderby = "UnitsOnOrder DESC";
        }

        if (idorder != null && idorder.equals("UnitsInStock1")) {
            orderby = "UnitsInStock ASC";
        }

        if (idorder != null && idorder.equals("UnitsInStock2")) {
            orderby = "UnitsInStock DESC";
        }

        sql = sql + " ORDER BY " + orderby + " OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, amount);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }
    
    
    /**
     * It gets the next 4 products from the database and returns them as a vector
     * 
     * @param amount the amount of products that have been loaded
     * @return A vector of Products
     */
    public Vector<Products> getNextReviewsProducts(int amount) {
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT Products.* FROM Products ORDER BY ProductID ASC OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        DAOReview daoReview = new DAOReview();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, amount);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrders = rs.getInt("UnitsOnOrder");
                double discount = rs.getDouble("Discount");
                boolean discontinued = rs.getBoolean("Discontinued");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    ProductInfo proInfo = daoProductInfo.getProductInfoByProductID(productID);
                    Vector<Review> reviews = daoReview.getAllReviewByProductIDAndAdmin(productID);
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discount,discontinued, base64Image, proInfo, reviews));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

}
