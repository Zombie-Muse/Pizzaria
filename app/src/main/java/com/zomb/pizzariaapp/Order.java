package com.zomb.pizzariaapp;

import java.util.ArrayList;

public class Order {

    private double sizePrice = 0;
    private double toppingPrice = 0;
    private double totalPrice = 0;
    private String pizzaSize, toppings;
    private String name, phone, address, email;


    public Order() {
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSizePrice() {
        return sizePrice;
    }

    public void setSizePrice(double sizePrice) {
        this.sizePrice = sizePrice;
    }

    public double getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(double toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double calculateTotal(double sPrice, double tPrice) {

        totalPrice = sPrice + tPrice;
        return totalPrice;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public String getToppings() {
        return toppings;
    }

    public String setTextInfo(String name, String address, String phone, String email, String pSize, String pToppings) {
        return "Order: \n" +
                name + ", " + address + ", " + email + "\n" +
                phone + "\n" +
                pSize + ", \n" +
                pToppings.replace(",", "\n");  //(?:[|,|])
    }

}
