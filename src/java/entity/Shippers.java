/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author daova
 */
public class Shippers {
    private int shipperID;
    private String companyName;
    private String phone;
    private String email;
    private boolean status;

    public Shippers(int shipperID, String companyName, String phone, String email, boolean status) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public Shippers(int shipperID, String companyName, String phone, String email, int id, boolean status) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Shippers(String companyName, String phone, String email, boolean status) {
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }


    
    
    public Shippers(int shipperID, String companyName, String phone, String email) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
    }

    public Shippers() {
    }

    public Shippers(int shipperID, String companyName, String phone, boolean status) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phone = phone;
        this.status = status;
    }

    public Shippers(String companyName, String phone, boolean status) {
        this.companyName = companyName;
        this.phone = phone;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    
}
