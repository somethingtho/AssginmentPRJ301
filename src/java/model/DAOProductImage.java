/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.ProductImage;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import javax.imageio.ImageIO;

/**
 *
 * @author daova
 */
public class DAOProductImage extends DBContext {

    /**
     * It takes a list of files and a product ID, and inserts the files into the
     * database
     *
     * @param fileParts List of Part objects
     * @param pid product id
     * @return The number of images that were added to the database.
     */
    public int AddImg(List<Part> fileParts, int pid) {
        int number = 0;
        String sql = "INSERT INTO ProductImage(ProductID, Image) VALUES(?, ?)";
        if (!fileParts.isEmpty()) {
            for (Part file : fileParts) {
                long fileSize = file.getSize();
                System.out.println(fileSize);
                String fileName = getFileName(file);
                if (file != null && fileSize > 1000 && isImageFile(fileName)) {
                    try {
                        PreparedStatement pre = connection.prepareStatement(sql);
                        pre.setInt(1, pid);
                        pre.setBinaryStream(2, file.getInputStream());
                        pre.executeUpdate();
                        number++;
                    } catch (Exception e) {
                    }
                }
            }
        }
        return number;
    }

    /**
     * It takes a Part object and returns the filename of the file that was
     * uploaded
     *
     * @param part The part of the request that contains the file.
     * @return The file name of the uploaded file.
     */
    public String getFileName(Part part) {
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
     * Delete a product image from the database
     *
     * @param id the id of the image
     * @return The number of rows affected by the SQL statement.
     */
    public int DeleteProductImg(int id) {
        int number = 0;
        String sql = "DELETE ProductImage WHERE ID =?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProductImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    /**
     * It gets all the images from the database and converts them to base64
     * strings
     *
     * @return A vector of ProductImage objects.
     */
    public Vector<ProductImage> getAllProductImage() {
        Vector<ProductImage> vector = new Vector<>();
        String sql = "SELECT * FROM ProductImage";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int productID = rs.getInt("ProductID");
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
                    vector.add(new ProductImage(id, productID, base64Image));
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
     * It gets all the images of a product from the database and returns them as
     * a vector of ProductImage objects
     *
     * @param productID the ID of the product
     * @return A vector of ProductImage objects.
     */
    public Vector<ProductImage> getAllImageProductByProductID(int productID) {
        Vector<ProductImage> vector = new Vector<>();
        String sql = "SELECT * FROM ProductImage WHERE ProductID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int pID = rs.getInt("ProductID");
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
                    vector.add(new ProductImage(id, pID, base64Image));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    /*
    
    //Add ProductImage
    
    public int InsertProductImage(int productID, InputStream file){
        int number = 0;
        String sql = "INSERT INTO ProductImage(ProductID, Image) VALUES(?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productID);

            if (file != null) {
                pre.setBlob(2, file);
            }
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    //Delete ProductImage
    public int DeleteProductImage(int id){
        int number = 0;
        String sql = "DELETE FROM ProductImage WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1,id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
        
    }
     */

     
    /**
     * It reads the file and returns true if it's a valid image, and false if it's not
     * 
     * @param file The file to check
     * @return A boolean value.
     */
    public boolean isImage(File file) throws IOException {
        try {
            // Read the image file to check if it's a valid image
            ImageIO.read(file);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * It checks if the file extension is an image file extension
     * 
     * @param filePath The path of the file to check
     * @return The file extension of the file path.
     */
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

    public static void main(String[] args) {
        DAOProductImage dao = new DAOProductImage();
        System.out.println(dao.DeleteProductImg(730));
    }
}
