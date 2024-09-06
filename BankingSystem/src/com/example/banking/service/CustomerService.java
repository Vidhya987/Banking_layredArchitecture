package com.example.banking.service;

import com.example.banking.dao.CustomerDAO;
import com.example.banking.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public boolean addCustomer(Customer customer) throws SQLException {
        return customerDAO.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomerById(Long id) throws SQLException {
        return customerDAO.getCustomerById(id);
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        return customerDAO.updateCustomer(customer);
    }

    public boolean deleteCustomer(Long id) throws SQLException {
        return customerDAO.deleteCustomer(id);
    }
}
