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
public class Orders {
    private int orderID, customerID;
    private String orderDate, requireDate, shippedDate;
    private Shippers shipper;
    private String address;
    private boolean payments;
    private int status;
    private Vector<OrderDetails> orderDetails;
    private double totalMoney;
    private Customers cus;
    private Orders od;

    public Orders(int orderID, String orderDate, String requireDate, String shippedDate, Shippers shipper, String address, boolean payments, int status, double totalMoney, Customers cus) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.requireDate = requireDate;
        this.shippedDate = shippedDate;
        this.shipper = shipper;
        this.address = address;
        this.payments = payments;
        this.status = status;
        this.totalMoney = totalMoney;
        this.cus = cus;
    }
    
    

    public Orders getOd() {
        return od;
    }

    public void setOd(Orders od) {
        this.od = od;
    }

    public Orders(Customers cus, Orders od, double totalMoney) {
        this.cus = cus;
        this.od = od;
        this.totalMoney=totalMoney;
    }
    
    public Orders(int orderID, int customerID, String orderDate, String requireDate, String shippedDate, Shippers shipper,  String address, boolean payments, int status, Vector<OrderDetails> orderDetails) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.requireDate = requireDate;
        this.shippedDate = shippedDate;
        this.shipper = shipper;
        this.address = address;
        this.payments = payments;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public Customers getCus() {
        return cus;
    }

    public void setCus(Customers cus) {
        this.cus = cus;
    }
    
    public Orders(int orderID, String orderDate, String shippedDate, String address, boolean payments, int status, double totalMoney, Customers cus) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.address = address;
        this.payments = payments;
        this.status = status;
        this.totalMoney = totalMoney;
        this.cus = cus;
    }

    public Vector<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Vector<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Orders(int orderID, String orderDate, String requireDate, String shippedDate, Shippers shipper, String address, boolean payments, int status, Vector<OrderDetails> orderDetails, double totalMoney, Customers cus) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.requireDate = requireDate;
        this.shippedDate = shippedDate;
        this.shipper = shipper;
        this.address = address;
        this.payments = payments;
        this.status = status;
        this.orderDetails = orderDetails;
        this.totalMoney = totalMoney;
        this.cus = cus;
    }

    public Orders() {
    }
    
    public Orders(int orderID, int customerID, String orderDate, String requireDate, String shippedDate, Shippers shipper, String address, boolean payments, int status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.requireDate = requireDate;
        this.shippedDate = shippedDate;
        this.shipper = shipper;
        this.address = address;
        this.payments = payments;
        this.status = status;
    }

    public boolean isPayments() {
        return payments;
    }

    public void setPayments(boolean payments) {
        this.payments = payments;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(String requireDate) {
        this.requireDate = requireDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Shippers getShipper() {
        return shipper;
    }

    public void setShipper(Shippers shipper) {
        this.shipper = shipper;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", customerID=" + customerID + ", orderDate=" + orderDate + ", requireDate=" + requireDate + ", shippedDate=" + shippedDate + ", shipper=" + shipper + ", address=" + address + ", payments=" + payments + ", status=" + status + ", orderDetails=" + orderDetails + ", totalMoney=" + totalMoney + ", cus=" + cus + '}';
    }

}
