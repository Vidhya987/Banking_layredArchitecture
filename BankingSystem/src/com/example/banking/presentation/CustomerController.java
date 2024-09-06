package com.example.banking.presentation;

import com.example.banking.dao.CustomerDAO;
import com.example.banking.entity.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private CustomerDAO customerDAO = new CustomerDAO();
    private Scanner scanner = new Scanner(System.in);

    public void displayCustomerMenu() {
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. View All Customers");
            System.out.println("5. View Customer by ID");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    viewAllCustomers();
                    break;
                case 5:
                    viewCustomerById();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);

        try {
            boolean result = customerDAO.addCustomer(customer);
            if (result) {
                System.out.println("Customer added successfully.");
            } else {
                System.out.println("Failed to add customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new customer email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);

        try {
            boolean result = customerDAO.updateCustomer(customer);
            if (result) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Failed to update customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        Long id = scanner.nextLong();

        try {
            boolean result = customerDAO.deleteCustomer(id);
            if (result) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Failed to delete customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewAllCustomers() {
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewCustomerById() {
        System.out.print("Enter customer ID to view: ");
        Long id = scanner.nextLong();

        try {
            Customer customer = customerDAO.getCustomerById(id);
            if (customer != null) {
                System.out.println(customer);
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
