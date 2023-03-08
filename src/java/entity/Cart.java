/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.IOException;
import java.util.Vector;
import model.DAOProductInfo;
import model.DAOProducts;

/**
 *
 * @author daova
 */
public class Cart {
    private Vector<Item>  items ;
    
    public Cart(){
        items = new Vector<>();
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void setItems(Vector<Item> items) {
        this.items = items;
    }
    
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    private Item getItemById(int id){
        for(Item i:items){
            if(i.getProduct().getProductID()==id){
                return i;
            }
        }
        return null;
    }
    public void addItem(Item t){
        if(getItemById(t.getProduct().getProductID())!=null){
            Item m=getItemById(t.getProduct().getProductID());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        }else
            items.add(t);
    }
    public void removeItem(int id){
        if(getItemById(id)!=null){
            items.remove(getItemById(id));
        }
    }
    public double getTotalMoney(){
        double t=0;
        for(Item i:items)
            t+=(i.getQuantity()*(i.getPrice()-i.getPrice()*i.getProduct().getDiscount()));
        return t;
    }
    private Products getProductById(int id,Vector<Products> list){
        for(Products i:list){
            if(i.getProductID()==id)
                return i;
        }
        return null;
    }
    
    public Cart(String txt,Vector<Products> list){
        items=new Vector<>();
        try{
        if(txt!=null && txt.length()!=0){
            String[] s=txt.split("/");
            for(String i:s){
                String[] n=i.split(":");
                int id=Integer.parseInt(n[0]);
                int quantity=Integer.parseInt(n[1]);
                Products p=getProductById(id, list);
                Item t=new Item(p,quantity, p.getUnitPrice());
                addItem(t);
            }
        }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Cart{" + "items=" + items + '}';
    }
    
    
    public static void main(String[] args) throws IOException {
        String txt = "1:1/32:1";
        DAOProducts daoProducts = new DAOProducts();
        DAOProductInfo daoProductInfo = new DAOProductInfo();
        Vector<Products> list = daoProducts.getAllProducts();
        Cart cart = new Cart(txt,list);
        System.out.println(cart.getTotalMoney());
        
    }
}
