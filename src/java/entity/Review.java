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
    private Products pro;
    private String contentSend;
    private int rate;
    private String dateRate;
    boolean status;

    public Products getPro() {
        return pro;
    }

    public void setPro(Products pro) {
        this.pro = pro;
    }

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

    public Review(Accounts acc, Customers cus, Products pro, String contentSend, int rate) {
        this.acc = acc;
        this.cus = cus;
        this.pro = pro;
        this.contentSend = contentSend;
        this.rate = rate;
    }

    public Review(Accounts acc, Products pro, String contentSend, int rate) {
        this.acc = acc;
        this.pro = pro;
        this.contentSend = contentSend;
        this.rate = rate;
    }

    
    public Review(int id, Accounts acc, Customers cus, Products pro, String contentSend, int rate, String dateRate, boolean status) {
        this.id = id;
        this.acc = acc;
        this.cus = cus;
        this.pro = pro;
        this.contentSend = contentSend;
        this.rate = rate;
        this.dateRate = dateRate;
        this.status = status;
    }

    
    public Review(Accounts acc, Customers cus, Products pro, String contentSend, int rate, String dateRate) {
        this.acc = acc;
        this.cus = cus;
        this.pro = pro;
        this.contentSend = contentSend;
        this.rate = rate;
        this.dateRate = dateRate;
    }

    public Review(int id, String contentSend, int rate) {
        this.id = id;
        this.contentSend = contentSend;
        this.rate = rate;
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
