/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.dao;

/**
 *
 * @author Ima
 */

import com.example.jsonxml.exception.CartNotFoundException;
import com.example.jsonxml.models.Cart;

import java.util.HashMap;
import java.util.Map;

public class CartDAO {
    private static Map<Integer, Cart> carts = new HashMap<>(); // Map to store carts with customer ID as the key

    // Create a new cart for a customer and return it
    public Cart getCartByCustomerId(int customerId) {
        return carts.getOrDefault(customerId, new Cart(customerId)); // If cart not found, create a new one
    }

    // Add an item to the cart for a specific customer
    public void addItemToCart(int customerId, int bookId, int quantity) {
        Cart cart = carts.computeIfAbsent(customerId, Cart::new); // Create a new cart if it doesn't exist
        cart.addItem(bookId, quantity); // Add the item to the cart
    }

    // Update the quantity of an item in the customer's cart
    public void updateCartItem(int customerId, int bookId, int quantity) {
        Cart cart = carts.get(customerId); // Retrieve the cart for the customer
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer ID " + customerId + " not found."); // If cart not found, throw an exception
        }
        cart.updateItemQuantity(bookId, quantity); // Update the item quantity in the cart
    }

    // Remove an item from the customer's cart
    public void removeItemFromCart(int customerId, int bookId) {
        Cart cart = carts.get(customerId); // Retrieve the cart for the customer
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer ID " + customerId + " not found."); // If cart not found, throw an exception
        }
        cart.removeItem(bookId); // Remove the item from the cart
    }
}
