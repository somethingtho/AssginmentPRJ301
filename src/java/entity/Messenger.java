/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class Messenger {
    private String email;
    private Customers customer;
    private String contentSend, contentRep;
    private int role;

    public Messenger(String email, String contentSend, String contentRep, int role) {
        this.email = email;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
        this.role = role;
    }

    public Messenger(Customers customer, String contentSend, String contentRep) {
        this.customer = customer;
        this.contentSend = contentSend;
        this.contentRep = contentRep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    
    
}
