/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author daova
 */
public class Customers {
    private int customerID;
    private boolean gender;
    private String customerName, phone, email, address;
    private Accounts acc;
    private int id;
    private String password;
    private String base64Image;
    private double totalMoney;

    public Customers(int customerID, boolean gender, String customerName, String phone) {
        this.customerID = customerID;
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
    }

    
    public Customers(int customerID, boolean gender, String customerName) {
        this.customerID = customerID;
        this.gender = gender;
        this.customerName = customerName;
    }
    
    public Customers(int id, String customerName, String base64Image) {
        this.customerName = customerName;
        this.id = id;
        this.base64Image = base64Image;
    }

    public Customers(int customerID, boolean gender, String customerName, String phone, String email, String address, Accounts acc, int id, String password, String base64Image, double totalMoney) {
        this.customerID = customerID;
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.acc = acc;
        this.id = id;
        this.password = password;
        this.base64Image = base64Image;
        this.totalMoney = totalMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    
    

    public Customers() {
    }

    public Customers(boolean gender, String customerName, String phone, String email, String address, Accounts acc) {
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.acc = acc;
    }

    
    public Customers(String customerName, String email, Accounts acc, String password) {
        this.customerName = customerName;
        this.email = email;
        this.acc = acc;
        this.password = password;
    }

    
    public Customers(int id, String base64Image) {
        this.id = id;
        this.base64Image = base64Image;
    }

    public Customers(int customerID, boolean gender, String customerName, String phone, String email, String address, Accounts acc, String base64Image) {
        this.customerID = customerID;
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.acc = acc;
        this.base64Image = base64Image;
    }
    
    

    public Accounts getAcc() {
        return acc;
    }

    public void setAcc(Accounts acc) {
        this.acc = acc;
    }

    
    public Customers(int customerID, boolean gender, String customerName, String phone, String email, String address, Accounts acc, int id, String password, String base64Image) {
        this.customerID = customerID;
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.acc = acc;
        this.id = id;
        this.password = password;
        this.base64Image = base64Image;
    }

    public Customers(boolean gender, String customerName, String phone, String email, String address, Accounts acc, int id, String password, String base64Image) {
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.acc = acc;
        this.id = id;
        this.password = password;
        this.base64Image = base64Image;
    }

    public Customers(int customerID, boolean gender, String customerName, String phone, String email, String address, Accounts acc, String password, String base64Image) {
        this.customerID = customerID;
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.acc = acc;
        this.password = password;
        this.base64Image = base64Image;
    }

    public Customers(boolean gender, String customerName, String phone, String email, String address, Accounts acc, String password, String base64Image) {
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.acc = acc;
        this.password = password;
        this.base64Image = base64Image;
    }

    public Customers(int customerID, boolean gender, String customerName, String phone, String email, String address, int id, String password, String base64Image) {
        this.customerID = customerID;
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.id = id;
        this.password = password;
        this.base64Image = base64Image;
    }

    public Customers(boolean gender, String customerName, String phone, String email, String address, int id, String password, String base64Image) {
        this.gender = gender;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.id = id;
        this.password = password;
        this.base64Image = base64Image;
    }

    
    
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public String toString() {
        return "Customers{" + "customerID=" + customerID + ", gender=" + gender + ", customerName=" + customerName + ", phone=" + phone + ", email=" + email + ", address=" + address + ", acc=" + acc + ", id=" + id + ", password=" + password + ", base64Image=" + base64Image + '}';
    }
    
    
    
}
