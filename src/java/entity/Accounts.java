/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author daova
 */
public class Accounts {
    private int id;
    private String userName;
    private int role;
    private String registrationDate;
    private boolean status;

    public Accounts(int id, String registrationDate, boolean status) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    
    
    public Accounts(String userName, int role, String registrationDate, boolean status) {
        this.userName = userName;
        this.role = role;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    
    public Accounts(int id, String userName, int role, String registrationDate, boolean status) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    

    public String getRegistrationDate() {
        return registrationDate;
    }

    
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Accounts() {
    }

    public Accounts(String userName) {
        this.userName = userName;
    }
    
    public Accounts(int id, String userName, int role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }

    public Accounts(String userName, int role) {
        this.userName = userName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Accounts{" + "id=" + id + ", userName=" + userName + ", role=" + role + ", registrationDate=" + registrationDate + ", status=" + status + '}';
    }

    
    
}
