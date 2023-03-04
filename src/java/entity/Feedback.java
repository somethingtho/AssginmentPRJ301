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
public class Feedback {
    private int id, idAccount;
    private String contentSend, contentRep;
    private boolean status;
    private Customers cus;
    private String email;
    private int role;
    private String dateSend, dateRep;
    private Suppliers supplier;
    private Shippers shipper;

    public Feedback(int id, String contentSend, String contentRep, boolean status, int role, String dateSend, String dateRep, Suppliers supplier) {
        this.id = id;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
        this.status = status;
        this.role = role;
        this.dateSend = dateSend;
        this.dateRep = dateRep;
        this.supplier = supplier;
    }

    public Feedback(int id, String contentSend, String contentRep, boolean status, int role, String dateSend, String dateRep, Shippers shipper) {
        this.id = id;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
        this.status = status;
        this.role = role;
        this.dateSend = dateSend;
        this.dateRep = dateRep;
        this.shipper = shipper;
    }

    
    
    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public Shippers getShipper() {
        return shipper;
    }

    public void setShipper(Shippers shipper) {
        this.shipper = shipper;
    }
    
    
    
    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getDateRep() {
        return dateRep;
    }

    public void setDateRep(String dateRep) {
        this.dateRep = dateRep;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", idAccount=" + idAccount + ", contentSend=" + contentSend + ", contentRep=" + contentRep + ", status=" + status + ", cus=" + cus + ", email=" + email + ", role=" + role + ", dateSend=" + dateSend + ", dateRep=" + dateRep + ", supplier=" + supplier + ", shipper=" + shipper + '}';
    }
    
    

    

    
    
    
    public Feedback(int id, String contentSend, String contentRep, boolean status, Customers cus, String email, int role, String dateSend, String dateRep) {
        this.id = id;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
        this.status = status;
        this.cus = cus;
        this.email = email;
        this.role = role;
        this.dateSend = dateSend;
        this.dateRep = dateRep;
    }

    public Feedback(int id, String contentSend, String contentRep, boolean status, String email, int role, String dateSend, String dateRep) {
        this.id = id;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
        this.status = status;
        this.email = email;
        this.role = role;
        this.dateSend = dateSend;
        this.dateRep = dateRep;
    }

    
    
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Feedback(int idAccount, String contentSend, String email, int role) {
        this.idAccount = idAccount;
        this.contentSend = contentSend;
        this.email = email;
        this.role = role;
    }

    public Feedback(String contentSend, String email, int role) {
        this.contentSend = contentSend;
        this.email = email;
        this.role = role;
    }
    
    
    
    public Feedback() {
    }

    public Feedback(int id, int idAccount, String contentSend, String contentRep, boolean status) {
        this.id = id;
        this.idAccount = idAccount;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
        this.status = status;
    }

    public Feedback(int idAccount, String contentSend, String contentRep, boolean status) {
        this.idAccount = idAccount;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getContentSend() {
        return contentSend;
    }

    public void setContentSend(String contentSend) {
        this.contentSend = contentSend;
    }

    public String getContentRep() {
        return contentRep;
    }

    public void setContentRep(String contentRep) {
        this.contentRep = contentRep;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Feedback(int idAccount, String contentSend) {
        this.idAccount = idAccount;
        this.contentSend = contentSend;
    }

    public Customers getCus() {
        return cus;
    }

    public void setCus(Customers cus) {
        this.cus = cus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    
    
    
}
