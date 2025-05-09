/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.models;

/**
 *
 * @author Ima
 */

import java.util.Date;
import java.util.Map;
// Annotations to control JSON date format
import com.fasterxml.jackson.annotation.JsonFormat;

public class Order {
    private int id;
    private int customerId;
    private Map<Integer, Integer> items; // Key: Book ID, Value: Quantity
    private double totalPrice;
    
    // No argument constructor
    public Order(){  
    }
    
    // Format the date properly when the object is serialized into JSON
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "'DATE:'yyyy-MM-dd 'TIME:'HH:mm:ss ")
    //  Date and time when the order was placed
    private Date orderDate;
    
    // Constructor with parameters
    public Order(int id, int customerId, Map<Integer, Integer> items, double totalPrice, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
