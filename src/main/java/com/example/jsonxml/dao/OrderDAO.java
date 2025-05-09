/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.dao;

/**
 *
 * @author Ima
 */

import com.example.jsonxml.models.Order;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDAO {
    private static List<Order> orders = new ArrayList<>(); // List to store orders
    private static int idCounter = 1; // Counter to generate unique IDs for orders

    // Create a new order and return it
    public Order createOrder(int customerId, Map<Integer, Integer> items, double totalPrice) {
        Order order = new Order(idCounter++, customerId, items, totalPrice, new Date()); // Create a new order with a unique ID
        orders.add(order); // Add the order to the list
        return order;
    }

    // Retrieves all orders for a specific customer based on customer ID
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orders.stream() // Filters and collects all orders that match the given customer ID
                .filter(order -> order.getCustomerId() == customerId) // Filters orders by customer ID
                .collect(Collectors.toList()); // Collects the filtered orders into a list
    }

    // Retrieves a specific order for a customer by its ID
    public Order getOrderById(int customerId, int orderId) {
        return orders.stream() // Filters the orders list to find the order with both the matching customer ID and order ID
                .filter(order -> order.getId() == orderId && order.getCustomerId() == customerId)
                .findFirst() // Gets the first matching order
                .orElseThrow(() -> new RuntimeException("Order with ID " + orderId + " not found for customer ID " + customerId)); // Throws an exception if the order is not found
    }
}
