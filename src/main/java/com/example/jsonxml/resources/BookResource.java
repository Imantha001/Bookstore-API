/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.resources;

/**
 *
 * @author Ima
 */


 import com.example.jsonxml.dao.BookDAO;
 import com.example.jsonxml.models.Book;
 
 import javax.ws.rs.*; // Import JAX-RS annotations
 import javax.ws.rs.core.MediaType; // Import MediaType for specifying response type
 import javax.ws.rs.core.Response; // Import Response for building HTTP responses
 
 // Base URI path for all endpoints the Book resource
 @Path("/books")
 @Produces(MediaType.APPLICATION_JSON) // Response type is JSON
 @Consumes(MediaType.APPLICATION_JSON) // Request type is JSON


 public class BookResource {
     private final BookDAO bookDAO = new BookDAO(); // Use BookDAO for data operations
 
     // Create a new book
     @POST
     public Response createBook(Book book) {
         Book createdBook = bookDAO.createBook(book); // Create a new book using BookDAO
         return Response.status(Response.Status.CREATED).entity(createdBook).build(); // Return 201 Created status with the created book
     }
 
     // Get all books
     @GET
     public Response getAllBooks() {
         return Response.ok(bookDAO.getAllBooks()).build(); // Return 200 OK status with the list of books
     }
 
     // Get a book by ID
     @GET
     @Path("/{id}")
     public Response getBookById(@PathParam("id") int id) {
         try {
             Book book = bookDAO.getBookById(id); //Fetch book by ID using BookDAO
             return Response.ok(book).build(); // Return 200 OK status with the book
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if book not found
         }
     }
 
     // Update a book by ID
     @PUT
     @Path("/{id}")
     public Response updateBook(@PathParam("id") int id, Book updatedBook) {
         try {
             Book book = bookDAO.updateBook(id, updatedBook); // Update book using BookDAO
             return Response.ok(book).build(); // Return 200 OK status with the updated book
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if book not found
         }
     }
 
     // Delete a book by ID
     @DELETE
     @Path("/{id}")
     public Response deleteBook(@PathParam("id") int id) {
         try {
             bookDAO.deleteBook(id); // Delete book using BookDAO
             return Response.noContent().build(); // Return 204 No Content status
         } catch (Exception e) {
             return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if book not found
         }
     }
 }
