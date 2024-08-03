/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Payment {

    String id, shopping_cart_id, account_id;

    public Payment() {
    }

    public Payment(String id, String shopping_cart_id, String account_id) {
        this.id = id;
        this.shopping_cart_id = shopping_cart_id;
        this.account_id = account_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopping_cart_id() {
        return shopping_cart_id;
    }

    public void setShopping_cart_id(String shopping_cart_id) {
        this.shopping_cart_id = shopping_cart_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

}
