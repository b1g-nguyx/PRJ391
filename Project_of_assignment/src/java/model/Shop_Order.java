/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Shop_Order {

    String id, date_order;
    float total_price;
    byte status_order;
    String address_shipping, shipping_id, account_id;

    public Shop_Order() {
    }

    public Shop_Order(String id, String date_order, float total_price, byte status_order, String address_shipping, String shipping_id, String account_id) {
        this.id = id;
        this.date_order = date_order;
        this.total_price = total_price;
        this.status_order = status_order;
        this.address_shipping = address_shipping;
        this.shipping_id = shipping_id;
        this.account_id = account_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public byte getStatus_order() {
        return status_order;
    }

    public void setStatus_order(byte status_order) {
        this.status_order = status_order;
    }

    public String getAddress_shipping() {
        return address_shipping;
    }

    public void setAddress_shipping(String address_shipping) {
        this.address_shipping = address_shipping;
    }

    public String getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

}
