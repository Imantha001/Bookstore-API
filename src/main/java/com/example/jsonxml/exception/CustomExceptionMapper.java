/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.exception;

/**
 *
 * @author Ima
 */

import javax.ws.rs.core.Response; // Importing necessary classes for JAX-RS
import javax.ws.rs.ext.ExceptionMapper; // Importing ExceptionMapper interface
import javax.ws.rs.ext.Provider; // Importing Provider annotation
 
// Custom exception mapper to handle various exceptions and return appropriate HTTP responses
@Provider
public class CustomExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        if (exception instanceof BookNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Book Not Found", exception.getMessage()))
                    .build();
        } else if (exception instanceof AuthorNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Author Not Found", exception.getMessage()))
                    .build();
        } else if (exception instanceof CustomerNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Customer Not Found", exception.getMessage()))
                    .build();
        } else if (exception instanceof InvalidInputException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Invalid Input", exception.getMessage()))
                    .build();
        } else if (exception instanceof OutOfStockException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Out of Stock", exception.getMessage()))
                    .build();
        } else if (exception instanceof CartNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Cart Not Found", exception.getMessage()))
                    .build();
        } else {
            // Default case for any other runtime exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Internal Server Error", exception.getMessage()))
                    .build();
        }
    }

    // Inner class to structure error responses
    private static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}
