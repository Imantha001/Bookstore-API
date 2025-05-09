/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.resources;

import com.example.jsonxml.dao.BookDAO;
/**
 *
 * @author Ima
 */


import com.example.jsonxml.dao.AuthorDAO;
import com.example.jsonxml.dao.BookDAO;
import com.example.jsonxml.models.Author;
import com.example.jsonxml.models.Book;

import javax.ws.rs.*; // Import JAX-RS annotations
import javax.ws.rs.core.MediaType; // Import MediaType for specifying response type
import javax.ws.rs.core.Response; // Import Response for building HTTP responses
import java.util.List; // Import List for handling collections

// Base URI path for all endpoints the Author resource
@Path("/authors") 
@Produces(MediaType.APPLICATION_JSON) // Response type is JSON
@Consumes(MediaType.APPLICATION_JSON) // Request type is JSON


public class AuthorResource {

    private final AuthorDAO authorDAO = new AuthorDAO(); // Use AuthorDAO for data operations

    // Create a new author
    @POST
    public Response createAuthor(Author author) {
        Author createdAuthor = authorDAO.createAuthor(author); // Create a new author using AuthorDAO
        return Response.status(Response.Status.CREATED).entity(createdAuthor).build(); // Return 201 Created status with the created author
    }

    // Get all authors
    @GET
    public Response getAllAuthors() {
        return Response.ok(authorDAO.getAllAuthors()).build(); // Return 200 OK status with the list of authors
    }

    // Get an author by ID
    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") int id) {
        try {
            Author author = authorDAO.getAuthorById(id); // Fetch author by ID using AuthorDAO
            return Response.ok(author).build(); // Return 200 OK status with the author
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if author not found
        }
    }

    // Update an author by ID
    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        try {
            Author author = authorDAO.updateAuthor(id, updatedAuthor); // Update author using AuthorDAO
            return Response.ok(author).build(); // Return 200 OK status with the updated author
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if author not found
        }
    }

    // Delete an author by ID
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        try {
            authorDAO.deleteAuthor(id); // Delete author using AuthorDAO
            return Response.noContent().build(); // Return 204 No Content status
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return 404 if author not found
        }
    }

    // Get all books by a specific author
    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        try {
            // Fetch all books from BookDAO
            BookDAO bookDAO = new BookDAO();
            // Filter books by author ID
            List<Book> booksByAuthor = bookDAO.getAllBooks().stream()
                    .filter(book -> book.getAuthorId() == id)
                    .toList();
            
            // If no books are found for the author, return a 404 response
            if (booksByAuthor.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No books found for author with ID " + id)
                        .build();
            }

            // Return 200 OK status with the list of books by the author
            return Response.ok(booksByAuthor).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 
                    .entity("An error occurred while fetching books for the author")
                    .build(); // Return 500 Internal Server Error if an exception occurs
        }
    }
}
