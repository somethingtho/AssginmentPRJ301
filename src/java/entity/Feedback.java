/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
