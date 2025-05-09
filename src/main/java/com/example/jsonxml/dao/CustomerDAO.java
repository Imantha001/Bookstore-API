/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsonxml.dao;

/**
 *
 * @author Ima
 */

import com.example.jsonxml.exception.CustomerNotFoundException;
import com.example.jsonxml.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static List<Customer> customers = new ArrayList<>(); // List to store customers
    private static int idCounter = 1; // Counter to generate unique IDs for customers

    // Creates a new customer and adds it to the list
    public Customer createCustomer(Customer customer) {
        customer.setId(idCounter++); // Assign a unique ID to the customer
        customers.add(customer); // Add the customer to the list
        return customer;
    }

    // Retrieves all customers from the list
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Retrieves a customer by their unique ID
    public Customer getCustomerById(int id) {
        return customers.stream() // search through the list of customers
                .filter(customer -> customer.getId() == id) // filter by ID
                .findFirst() // get the first matching customer
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found.")); // throw an exception if not found
    }

    // Updates an existing customer by ID and returns the updated customer
    public Customer updateCustomer(int id, Customer updatedCustomer) {
        Customer customer = getCustomerById(id); // Retrieve the customer to be updated
        customer.setName(updatedCustomer.getName()); 
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPassword(updatedCustomer.getPassword());
        return customer;
    }

    // Deletes a customer by ID, throwing an exception if not found
    public void deleteCustomer(int id) {
        boolean removed = customers.removeIf(customer -> customer.getId() == id); // Remove the customer from the list
        if (!removed) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found."); // Throw exception if customer is not found
        }
    }
}
