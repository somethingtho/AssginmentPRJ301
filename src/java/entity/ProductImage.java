/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author daova
 */
public class ProductImage {
    private int id, productID;
    private String base64Image;

    public ProductImage() {
    }

    public ProductImage(int id, int productID, String base64Image) {
        this.id = id;
        this.productID = productID;
        this.base64Image = base64Image;
    }

    public ProductImage(int productID, String base64Image) {
        this.productID = productID;
        this.base64Image = base64Image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    
    
}
