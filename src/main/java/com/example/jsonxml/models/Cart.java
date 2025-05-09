/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.models;

/**
 *
 * @author Ima
 */

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private int customerId;
    // Map  key: bookId, value: quantity 
    private Map<Integer, Integer> items;


    // Constructor to initialize the cart with a specific customer ID
    public Cart(int customerId) {
        this.customerId = customerId;
        this.items = new HashMap<>();
    }
    
    // No argument constructor
    public Cart(){
        
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    // setter method to replace the items map
    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    // Method to add a book to the cart or increase quantity if already exists
    public void addItem(int bookId, int quantity) {
        items.put(bookId, items.getOrDefault(bookId, 0) + quantity);
    }

    // Method to remove a book completely from the cart
    public void removeItem(int bookId) {
        items.remove(bookId);
    }

    // Update the quantity of an existing book in the cart
    public void updateItemQuantity(int bookId, int quantity) {
        if (items.containsKey(bookId)) {
            items.put(bookId, quantity);
        }
    }
}
