/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.resources;

/**
 *
 * @author Ima
 */

import com.example.jsonxml.dao.OrderDAO;
import com.example.jsonxml.models.Order;

import javax.ws.rs.*; // Import JAX-RS annotations
import javax.ws.rs.core.MediaType; // Import MediaType for specifying response type
import javax.ws.rs.core.Response; // Import Response for building HTTP responses
import java.util.*;

// Base URI path for all endpoints the Order resource
@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON) // Response type is JSON
@Consumes(MediaType.APPLICATION_JSON) // Request type is JSON


public class OrderResource {

    private final OrderDAO orderDAO = new OrderDAO(); // Use OrderDAO for data operations

    // Create a new order for a customer
    @POST
    // Path for creating a new order
    public Response createOrder(@PathParam("customerId") int customerId, Map<String, Object> orderDetails) {
        // Safely parse the items field
        Map<String, Integer> stringItems = (Map<String, Integer>) orderDetails.get("items");
        Map<Integer, Integer> items = new HashMap<>();

        // Convert keys from String to Integer
        for (Map.Entry<String, Integer> entry : stringItems.entrySet()) {
            items.put(Integer.parseInt(entry.getKey()), entry.getValue());
        }

        // Parse the "totalPrice" field
        double totalPrice = (double) orderDetails.get("totalPrice");

        // Create a new order using OrderDAO
        Order order = orderDAO.createOrder(customerId, items, totalPrice);

        // Return 201 Created status with the created order
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    // Get all orders for a customer
    @GET
    public Response getOrders(@PathParam("customerId") int customerId) {
        List<Order> customerOrders = orderDAO.getOrdersByCustomerId(customerId); // Fetch all orders for the customer
        if (customerOrders.isEmpty()) { // Check if the list is empty
            return Response.status(Response.Status.NOT_FOUND).entity("No orders found for customer ID " + customerId).build(); // Return 404 if no orders found
        }
        return Response.ok(customerOrders).build(); // Return 200 OK status with the list of orders
    }

    // Get an order by ID for a customer
    @GET
    @Path("/{orderId}")
    public Response getOrderById(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        try {
            Order order = orderDAO.getOrderById(customerId, orderId); // Fetch the order by ID using OrderDAO
            return Response.ok(order).build(); // Return 200 OK status with the order
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if order not found
        }
    }
}
