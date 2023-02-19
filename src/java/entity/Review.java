/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author daova
 */
public class Review {
    private int id;
    private Accounts acc;
    private Customers cus;
    private int productID;
    private String contentSend;
    private int rate;
    private String dateRate;
    boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    public String getDateRate() {
        return dateRate;
    }

    public void setDateRate(String dateRate) {
        this.dateRate = dateRate;
    }

    public Review(Accounts acc, Customers cus, int productID, String contentSend, int rate) {
        this.acc = acc;
        this.cus = cus;
        this.productID = productID;
        this.contentSend = contentSend;
        this.rate = rate;
    }

    public Review(Accounts acc, int productID, String contentSend, int rate) {
        this.acc = acc;
        this.productID = productID;
        this.contentSend = contentSend;
        this.rate = rate;
    }

    
    public Review(int id, Accounts acc, Customers cus, int productID, String contentSend, int rate, String dateRate) {
        this.id = id;
        this.acc = acc;
        this.cus = cus;
        this.productID = productID;
        this.contentSend = contentSend;
        this.rate = rate;
        this.dateRate = dateRate;
    }

    public Review(Accounts acc, Customers cus, int productID, String contentSend, int rate, String dateRate) {
        this.acc = acc;
        this.cus = cus;
        this.productID = productID;
        this.contentSend = contentSend;
        this.rate = rate;
        this.dateRate = dateRate;
    }
    
    
    
    
    public Review() {
    }

    public Customers getCus() {
        return cus;
    }

    public void setCus(Customers cus) {
        this.cus = cus;
    }
    
    public Accounts getAcc() {
        return acc;
    }

    public void setAcc(Accounts acc) {
        this.acc = acc;
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

    public String getContentSend() {
        return contentSend;
    }

    public void setContentSend(String contentSend) {
        this.contentSend = contentSend;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

        
}
