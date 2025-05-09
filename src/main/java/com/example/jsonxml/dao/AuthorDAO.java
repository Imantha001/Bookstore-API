/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.dao;

/**
 *
 * @author Ima
 */

import com.example.jsonxml.exception.AuthorNotFoundException;
import com.example.jsonxml.models.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private static List<Author> authors = new ArrayList<>(); // List to store authors
    private static int idCounter = 1; // Counter to generate unique IDs for authors

    // Create a new author and return the created one with assigned ID
    public Author createAuthor(Author author) {
        author.setId(idCounter++); // Assign a unique ID to the author
        authors.add(author); // Add the author to the list
        return author; // Return the created author
    }

    // Retrieve all authors from the list
    public List<Author> getAllAuthors() {
        return authors;
    }

    // Retrieve an author by ID, throwing an exception if not found
    public Author getAuthorById(int id) {
        return authors.stream() // Stream through the list of authors
                .filter(author -> author.getId() == id) // Filter authors by ID
                .findFirst() // Get the first matching author
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found.")); // Throw exception if not found
    }

    // Update an existing author by ID and return the updated author
    public Author updateAuthor(int id, Author updatedAuthor) {
        Author author = getAuthorById(id); // Retrieve the author to be updated
        author.setName(updatedAuthor.getName()); // Update the author's name
        author.setBiography(updatedAuthor.getBiography()); // Update the author's biography
        return author;
    }

    // Delete an author by ID, throwing an exception if not found
    public void deleteAuthor(int id) {
        boolean removed = authors.removeIf(author -> author.getId() == id); // Remove the author from the list
        if (!removed) { // If no author was removed, throw an exception
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
    }
}
