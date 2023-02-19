/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import entity.ProductInfo;
import entity.Products;
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
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daova
 */
public class DAOProducts extends DBContext {

    public static void main(String[] args) throws IOException {
        DAOProducts dao = new DAOProducts();
        int[] id = {1,2};
        dao.getProductsBySuppliers(id, "smartphone", "1", 1, 100000000);
    }

    public int AddNewProduct(Products product, List<Part> input, Part filePart) throws IOException {
        int number = 0;
        DAOProductImage daoImg = new DAOProductImage();
        DAOProducts daoProducts = new DAOProducts();
        DAOProductInfo daoInfo = new DAOProductInfo();
        String sql = "INSERT INTO dbo.Products(ProductName, SupplierID, CategoryID, UnitPrice, UnitsInStock, UnitsOnOrder, Discontinued, Image) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getSupplier().getSupplierID());
            pre.setInt(3, product.getCategory().getCategoryID());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getUnitsInStock());
            pre.setInt(6, product.getUnitsOnOrder());
            pre.setBoolean(7, product.isDiscontinued());
            if (filePart != null && !getFileName(filePart).isEmpty()) {
                pre.setBinaryStream(8, filePart.getInputStream());
            } else {
                pre.setBinaryStream(8, null);
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
                        product = new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String base64Image = null;
                    Suppliers supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                    Categories category = daoCategories.getCategoryByCategoryID(categoryID);
                    product = new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return product;

    }

    public int UpdateProduct(Products product, List<Part> input, Part filePart) throws IOException {
        int number = 0;

        String sql = "UPDATE Products SET ProductName = ?, SupplierID = ?, CategoryID = ?, UnitPrice = ?, UnitsInStock = ?, Discontinued = ? WHERE ProductID = ?";
        if (filePart != null && !getFileName(filePart).isEmpty()) {
            sql = "UPDATE Products SET ProductName = ?, SupplierID = ?, CategoryID = ?, UnitPrice = ?, UnitsInStock = ?, Discontinued = ?, Image = ? WHERE ProductID = ?";
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getSupplier().getSupplierID());
            pre.setInt(3, product.getCategory().getCategoryID());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getUnitsInStock());
            pre.setBoolean(6, product.isDiscontinued());
            if (filePart != null && !getFileName(filePart).isEmpty()) {
                pre.setBinaryStream(7, filePart.getInputStream());
                pre.setInt(8, product.getProductID());
            } else {
                pre.setInt(7, product.getProductID());
            }
            number = pre.executeUpdate();

            DAOProductImage daoProImg = new DAOProductImage();
            DAOProductInfo daoProInfo = new DAOProductInfo();
            daoProInfo.UpdateProductInfo(product.getProInfo());
            number += daoProImg.AddImg(input, 80);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return number;
    }

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

    public Vector<Products> getAllProductsByAdmin() throws IOException {
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

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

    //list All products
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> getLaptopHotSale() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 4 * FROM Products WHERE CategoryID = "
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> getSmartPhoneHotSale() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 4 * FROM Products WHERE CategoryID = "
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> getTabletHotSale() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOP 4 * FROM Products WHERE CategoryID = "
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> getAllLaptop() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Products WHERE CategoryID = (SELECT CategoryID FROM dbo.Categories WHERE CategoryName = 'Laptop')";
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> getAllTablet() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Products WHERE CategoryID = (SELECT CategoryID FROM dbo.Categories WHERE CategoryName = 'Tablet')";
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> getAllMobile() {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Products WHERE CategoryID = (SELECT CategoryID FROM dbo.Categories WHERE CategoryName = 'SmartPhone')";
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> getListByPage(Vector<Products> vector,
            int start, int end) {
        Vector<Products> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(vector.get(i));
        }
        return arr;
    }

    public Vector<Products> getProductsBySuppliers(int[] id, String categoryName, String idorder) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE CategoryID =  (SELECT CategoryID FROM Categories WHERE CategoryName = ?)";
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> SearchByNames(String name) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE p.ProductName like ?";

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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
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

    public Vector<Products> getProductsBySuppliers(int[] id, String categoryName, String idorder, double from, double to) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE CategoryID =  (SELECT CategoryID FROM Categories WHERE CategoryName = ?)";
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> SearchByNames(String name, String idorder, double from, double to) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products p WHERE p.ProductName like ? ";
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Products getProductByID(int pID) {
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
                    p = new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public Vector<Products> getAllProductsSame(int supid, int cateid, int pid) {
        DAOSuppliers daoSuppliers = new DAOSuppliers();
        DAOCategories daoCategories = new DAOCategories();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT TOp 4 * FROM dbo.Products WHERE SupplierID  = ? AND CategoryID = ? AND ProductID <> ?";
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
                    vector.add(new Products(productID, productName, supplier, category, unitprice, unitsInStock, unitsOnOrders, discontinued, base64Image, proInfo));
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
