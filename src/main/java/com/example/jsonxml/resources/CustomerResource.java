/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.resources;

/**
 *
 * @author Ima
 */


 import com.example.jsonxml.dao.CustomerDAO;
 import com.example.jsonxml.models.Customer;
 
 import javax.ws.rs.*; // Import JAX-RS annotations
 import javax.ws.rs.core.MediaType; // Import MediaType for specifying response type
 import javax.ws.rs.core.Response; // Import Response for building HTTP responses
 
 // Base URI path for all endpoints the Customer resource
 @Path("/customers")
 @Produces(MediaType.APPLICATION_JSON) // Response type is JSON
 @Consumes(MediaType.APPLICATION_JSON) // Request type is JSON


 public class CustomerResource {
     private final CustomerDAO customerDAO = new CustomerDAO(); // Use CustomerDAO for data operations
 
     // Create a new customer
     @POST
     public Response createCustomer(Customer customer) {
         Customer createdCustomer = customerDAO.createCustomer(customer); // Create a new customer using CustomerDAO
         return Response.status(Response.Status.CREATED).entity(createdCustomer).build(); // Return 201 Created status with the created customer
     }
 
     // Get all customers
     @GET
     public Response getAllCustomers() {
         return Response.ok(customerDAO.getAllCustomers()).build();
     }
 
     // Get a customer by ID
     @GET
     @Path("/{id}")
     public Response getCustomerById(@PathParam("id") int id) {
         try {
             Customer customer = customerDAO.getCustomerById(id); // Fetch the customer by ID using CustomerDAO
             return Response.ok(customer).build(); // Return 200 OK status with the customer
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if customer not found
         }
     }
 
     // Update a customer by ID
     @PUT
     @Path("/{id}")
     public Response updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
         try {
             Customer customer = customerDAO.updateCustomer(id, updatedCustomer); // Update the customer using CustomerDAO
             return Response.ok(customer).build(); // Return 200 OK status with the updated customer
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if customer not found
         }
     }
 
     // Delete a customer by ID
     @DELETE
     @Path("/{id}")
     public Response deleteCustomer(@PathParam("id") int id) {
         try {
             customerDAO.deleteCustomer(id); // Delete the customer using CustomerDAO
             return Response.noContent().build(); // Return 204 No Content status
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if customer not found
         }
     }
 }
