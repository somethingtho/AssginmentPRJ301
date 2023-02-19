/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author daova
 */
public class ProductInfo {
    private int productID;
    private String size, weight, substance, cpu, ram, screen, camera, 
            graphicsCard, hardDrive, os, batteryCapacity, dateCreated;

    public ProductInfo(int productID, String size, String weight, String substance, String cpu, String ram, String screen, String camera, String graphicsCard, String hardDrive, String os, String batteryCapacity, String dateCreated) {
        this.productID = productID;
        this.size = size;
        this.weight = weight;
        this.substance = substance;
        this.cpu = cpu;
        this.ram = ram;
        this.screen = screen;
        this.camera = camera;
        this.graphicsCard = graphicsCard;
        this.hardDrive = hardDrive;
        this.os = os;
        this.batteryCapacity = batteryCapacity;
        this.dateCreated = dateCreated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    
    
    public ProductInfo() {
    }

    public ProductInfo(String size, String weight, String substance, String cpu, String ram, String screen, String camera, String graphicsCard, String hardDrive, String os, String batteryCapacity, String dateCreated) {
        this.size = size;
        this.weight = weight;
        this.substance = substance;
        this.cpu = cpu;
        this.ram = ram;
        this.screen = screen;
        this.camera = camera;
        this.graphicsCard = graphicsCard;
        this.hardDrive = hardDrive;
        this.os = os;
        this.batteryCapacity = batteryCapacity;
        this.dateCreated = dateCreated;
    }
    
    

    public ProductInfo(int productID, String size, String weight, String substance, String cpu, String ram, String screen, String camera, String graphicsCard, String hardDrive, String os, String batteryCapacity) {
        this.productID = productID;
        this.size = size;
        this.weight = weight;
        this.substance = substance;
        this.cpu = cpu;
        this.ram = ram;
        this.screen = screen;
        this.camera = camera;
        this.graphicsCard = graphicsCard;
        this.hardDrive = hardDrive;
        this.os = os;
        this.batteryCapacity = batteryCapacity;
    }

    public ProductInfo(String size, String weight, String substance, String cpu, String ram, String screen, String camera, String graphicsCard, String hardDrive, String os, String batteryCapacity) {
        this.size = size;
        this.weight = weight;
        this.substance = substance;
        this.cpu = cpu;
        this.ram = ram;
        this.screen = screen;
        this.camera = camera;
        this.graphicsCard = graphicsCard;
        this.hardDrive = hardDrive;
        this.os = os;
        this.batteryCapacity = batteryCapacity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
    
}
