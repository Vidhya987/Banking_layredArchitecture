package com.example.banking.presentation;

import com.example.banking.dao.AccountDAO;
import com.example.banking.entity.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountController {

    private AccountDAO accountDAO = new AccountDAO();
    private Scanner scanner = new Scanner(System.in);

    public void displayAccountMenu() {
        while (true) {
            System.out.println("Account Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View All Accounts");
            System.out.println("4. View Account by ID");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    viewAllAccounts();
                    break;
                case 4:
                    viewAccountById();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void deposit() {
        System.out.print("Enter account ID to deposit into: ");
        Long accountId = scanner.nextLong();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        try {
            boolean result = accountDAO.deposit(accountId, amount);
            if (result) {
                System.out.println("Deposit successful.");
            } else {
                System.out.println("Failed to deposit.");
            }
        } catch(SQLException e) {
            System.err.println("SQL error occurred during deposit.");
            e.printStackTrace();
        }
    }

    private void withdraw() {
        System.out.print("Enter account ID to withdraw from: ");
        Long accountId = scanner.nextLong();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        try {
            boolean result = accountDAO.withdraw(accountId, amount);
            if (result) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Failed to withdraw.");
            }
        } catch(SQLException e) {
            System.err.println("SQL error occurred during withdrawal.");
            e.printStackTrace();
        }
    }

    private void viewAllAccounts() {
        try {
            List<Account> accounts = accountDAO.getAllAccounts();
            for (Account account : accounts) {
                System.out.println(account); // Ensure `toString()` is properly overridden in `Account` class
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while fetching accounts.");
            e.printStackTrace();
        }
    }

    private void viewAccountById() {
        System.out.print("Enter account ID to view: ");
        Long id = scanner.nextLong();

        try {
            Account account = accountDAO.getAccountById(id);
            if (account != null) {
                System.out.println(account); // Ensure `toString()` is properly overridden in `Account` class
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while fetching account by ID.");
            e.printStackTrace();
        }
    }
}
