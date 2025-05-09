/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.dao;

/**
 *
 * @author Ima
 */

import com.example.jsonxml.exception.BookNotFoundException;
import com.example.jsonxml.models.Book;

import java.util.ArrayList;
import java.util.List;


public class BookDAO {
    private static List<Book> books = new ArrayList<>(); // List to store books
    private static int idCounter = 1; // Counter to generate unique IDs for books

    // Methods to manage books in the list
    public Book createBook(Book book) {
        book.setId(idCounter++); // Assign a unique ID to the book
        books.add(book); // Add the book to the list
        return book;
    }

    // Retrieve all books from the list
    public List<Book> getAllBooks() {
        return books;
    }

    // Retrieve a book by ID, throwing an exception if not found
    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id) // Filter books by ID
                .findFirst() // Get the first matching book
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found.")); // Throw exception if not found
    }

    // Update an existing book by ID and return the updated book
    public Book updateBook(int id, Book updatedBook) {
        Book book = getBookById(id); // Retrieve the book to be updated
        book.setTitle(updatedBook.getTitle());
        book.setAuthorId(updatedBook.getAuthorId());
        book.setIsbn(updatedBook.getIsbn());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setPrice(updatedBook.getPrice());
        book.setStock(updatedBook.getStock());
        return book;
    }

    // Delete a book by ID, throwing an exception if not found
    public void deleteBook(int id) {
        boolean removed = books.removeIf(book -> book.getId() == id); // Remove the book from the list
        if (!removed) {
            throw new BookNotFoundException("Book with ID " + id + " not found."); // If no book was removed, throw an exception
        }
    }

}
