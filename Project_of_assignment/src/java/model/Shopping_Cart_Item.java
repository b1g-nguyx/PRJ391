/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Shopping_Cart_Item {

    String id;
    int quantity;
    String product_id, shopping_cart_id;

    public Shopping_Cart_Item() {
    }

    public Shopping_Cart_Item(String id, int quantity, String product_id, String shopping_cart_id) {
        this.id = id;
        this.quantity = quantity;
        this.product_id = product_id;
        this.shopping_cart_id = shopping_cart_id;
    }

    public Shopping_Cart_Item(int quantity, String product_id) {
        this.quantity = quantity;
        this.product_id = product_id;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getShopping_cart_id() {
        return shopping_cart_id;
    }

    public void setShopping_cart_id(String shopping_cart_id) {
        this.shopping_cart_id = shopping_cart_id;
    }

}
