/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.resources;

/**
 *
 * @author Ima
 */


 import com.example.jsonxml.dao.CartDAO;
 import com.example.jsonxml.models.Cart;
 
 import javax.ws.rs.*; // Import JAX-RS annotations
 import javax.ws.rs.core.MediaType; // Import MediaType for specifying response type
 import javax.ws.rs.core.Response; // Import Response for building HTTP responses
 import java.util.Map;
 
 // Base URI path for all endpoints the Cart resource
 @Path("/customers/{customerId}/cart")
 @Produces(MediaType.APPLICATION_JSON) // Response type is JSON
 @Consumes(MediaType.APPLICATION_JSON) // Request type is JSON


 public class CartResource {
     private final CartDAO cartDAO = new CartDAO(); // Use CartDAO for data operations
 
     // Create a new cart for a customer
     @POST
     @Path("/items")
     public Response addItemToCart(@PathParam("customerId") int customerId, Map<String, Integer> item) {
         int bookId = item.get("bookId"); // Extract bookId from the request body
         int quantity = item.get("quantity"); // Extract quantity from the request body
 
         cartDAO.addItemToCart(customerId, bookId, quantity); // Add item to the cart using CartDAO
         Cart cart = cartDAO.getCartByCustomerId(customerId); // Fetch the updated cart
 
         return Response.ok(cart).build(); // Return 200 OK status with the updated cart
     }
 
     // Get the cart for a customer
     @GET
     public Response getCart(@PathParam("customerId") int customerId) {
         Cart cart = cartDAO.getCartByCustomerId(customerId); // Fetch the cart for the customer
         if (cart == null || cart.getItems().isEmpty()) {
             return Response.status(Response.Status.NOT_FOUND).entity("Cart not found or empty").build(); // Return 404 if cart not found or empty
         }
         return Response.ok(cart).build(); // Return 200 OK status with the cart
     }
 
     // Update an item in the cart
     @PUT
     @Path("/items/{bookId}")
     public Response updateCartItem(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId, Map<String, Integer> item) { 
         int quantity = item.get("quantity"); // Extract quantity from the request body
 
         try {
             cartDAO.updateCartItem(customerId, bookId, quantity); // Update item in the cart using CartDAO
             Cart cart = cartDAO.getCartByCustomerId(customerId); // Fetch the updated cart
             return Response.ok(cart).build(); // Return 200 OK status with the updated cart
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if item not found
         }
     }
 
     // Remove an item from the cart
     @DELETE
     @Path("/items/{bookId}")
     // @PathParam = Extracts the bookId from the URL path
     public Response removeItemFromCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
         try {
             cartDAO.removeItemFromCart(customerId, bookId); // Remove item from the cart using CartDAO
             Cart cart = cartDAO.getCartByCustomerId(customerId); // Fetch the updated cart
             return Response.ok(cart).build(); // Return 200 OK status with the updated cart
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if item not found
         }
     }
 }
