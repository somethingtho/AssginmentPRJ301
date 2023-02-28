/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author daova
 */
public class Suppliers {
    private int supplierID;
    private String companyName, phone, email,homePage;
    private int number;
    private Suppliers sup;
    private Categories cate;
    private boolean status;

    public Suppliers(Suppliers sup, Categories cate, int number) {
        this.sup = sup;
        this.cate = cate;
        this.number=number;
    }

    public Categories getCate() {
        return cate;
    }

    public void setCate(Categories cate) {
        this.cate = cate;
    }

    public Suppliers(int supplierID, String companyName, String phone, String email, String homePage, boolean status) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.homePage = homePage;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    public Suppliers(int number, Suppliers sup) {
        this.number = number;
        this.sup = sup;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Suppliers getSup() {
        return sup;
    }

    public void setSup(Suppliers sup) {
        this.sup = sup;
    }

    public Suppliers(int supplierID, String companyName, String phone, String email, String homePage) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.homePage = homePage;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Suppliers(String companyName, String phone, String email, String homePage, boolean status) {
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.homePage = homePage;
        this.status = status;
    }

    
    
    
    public Suppliers() {
    }

    public Suppliers(int supplierID, String companyName, String homePage) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.homePage = homePage;
    }

    public Suppliers(int supplierID, String companyName, String phone, String homePage) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.phone = phone;
        this.homePage = homePage;
    }

    public Suppliers(int supplierID, String companyName) {
        this.supplierID = supplierID;
        this.companyName = companyName;
    }
    

    public Suppliers(String companyName, String phone, String homePage) {
        this.companyName = companyName;
        this.phone = phone;
        this.homePage = homePage;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
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

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    public String toString() {
        return "Suppliers{" + "supplierID=" + supplierID + ", companyName=" + companyName + ", phone=" + phone + ", email=" + email + ", homePage=" + homePage + ", number=" + number + ", sup=" + sup + ", cate=" + cate + ", status=" + status + '}';
    }

    

    
    
}
