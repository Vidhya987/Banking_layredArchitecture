package com.example.banking.main;

import com.example.banking.presentation.CustomerController;
import com.example.banking.presentation.AccountController;

public class Main {
    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        AccountController accountController = new AccountController();

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Customer Menu");
            System.out.println("2. Account Menu");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    customerController.displayCustomerMenu();
                    break;
                case 2:
                    accountController.displayAccountMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
