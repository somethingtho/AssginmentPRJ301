/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Vector;

/**
 *
 * @author daova
 */
public class Products {
    private int productID;
    private String productName;
    private Suppliers supplier;
    private Categories category;
    private double unitPrice;
    private int unitsInStock, unitsOnOrder;
    private double discount;
    private boolean discontinued;
    private String base64Image;
    private ProductInfo proInfo;
    private Vector<Review> reviews;

    public Products(int productID, String productName, Suppliers supplier, Categories category, double unitPrice, int unitsInStock, int unitsOnOrder, double discount,boolean discontinued, String base64Image, ProductInfo proInfo, Vector<Review> reviews) {
        this.productID = productID;
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.discontinued = discontinued;
        this.base64Image = base64Image;
        this.proInfo = proInfo;
        this.reviews = reviews;
    }
    
    
    public Products() {
    }

    public Vector<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Vector<Review> reviews) {
        this.reviews = reviews;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    
    
    
    public Products(int productID, String productName, Suppliers supplier, Categories category, double unitPrice, int unitsInStock, int unitsOnOrder, double discount,boolean discontinued, String base64Image) {
        this.productID = productID;
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.discontinued = discontinued;
        this.base64Image = base64Image;
    }

    
    
    public Products(String productName, Suppliers supplier, Categories category, double unitPrice, int unitsInStock, int unitsOnOrder, double discount,boolean discontinued, ProductInfo proInfo) {
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.discount = discount;
        this.discontinued = discontinued;
        this.proInfo = proInfo;
    }
    
    

    public Products(int productID, String productName, Suppliers supplier, Categories category, double unitPrice, int unitsInStock, int unitsOnOrder, double discount,boolean discontinued, String base64Image, ProductInfo proInfo) {
        this.productID = productID;
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.discontinued = discontinued;
        this.discount = discount;
        this.base64Image = base64Image;
        this.proInfo = proInfo;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    

    public ProductInfo getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProductInfo proInfo) {
        this.proInfo = proInfo;
    }

    
    
    

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" + productName + ", supplier=" + supplier + ", category=" + category + ", unitPrice=" + unitPrice + ", unitsInStock=" + unitsInStock + ", unitsOnOrder=" + unitsOnOrder + ", discontinued=" + discontinued + ", base64Image=" + base64Image + ", proInfo=" + proInfo + '}';
    }

    

    
    
    
}
