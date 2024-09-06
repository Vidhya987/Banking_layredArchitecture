package com.example.banking.dao;

import com.example.banking.entity.Customer;
import com.example.banking.helper.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Create a new customer
    public boolean addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customer (name, email) VALUES (?, ?)";
        try (Connection connection = DatabaseHelper.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Read all customers
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (Connection connection = DatabaseHelper.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }
        }
        return customers;
    }

    // Read customer by ID
    public Customer getCustomerById(Long id) throws SQLException {
        String query = "SELECT * FROM customer WHERE id = ?";
        try (Connection connection = DatabaseHelper.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getLong("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setEmail(resultSet.getString("email"));
                    return customer;
                }
            }
        }
        return null;
    }

    // Update an existing customer
    public boolean updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE customer SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseHelper.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setLong(3, customer.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Delete a customer
    public boolean deleteCustomer(Long id) throws SQLException {
        String query = "DELETE FROM customer WHERE id = ?";
        try (Connection connection = DatabaseHelper.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
