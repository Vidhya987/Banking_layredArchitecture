package com.example.banking.dao;

import com.example.banking.entity.Account;
import com.example.banking.helper.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // Fetch all accounts from the database
    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account";
        try (Connection connection = DatabaseHelper.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setAccountNumber(resultSet.getLong("account_number"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setCustomerId(resultSet.getLong("customer_id"));
                accounts.add(account);
            }
        }
        return accounts;
    }

    // Fetch account by ID from the database
    public Account getAccountById(Long id) throws SQLException {
        String query = "SELECT * FROM account WHERE id = ?";
        try (Connection connection = DatabaseHelper.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setId(resultSet.getLong("id"));
                    account.setAccountNumber(resultSet.getLong("account_number"));
                    account.setBalance(resultSet.getDouble("balance"));
                    account.setCustomerId(resultSet.getLong("customer_id"));
                    return account;
                }
            }
        }
        return null;
    }

    // Deposit amount into an account
    public boolean deposit(Long accountId, double amount) throws SQLException {
        String query = "UPDATE account SET balance = balance + ? WHERE id = ?";
        try (Connection connection = DatabaseHelper.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setLong(2, accountId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Withdraw amount from an account
    public boolean withdraw(Long accountId, double amount) throws SQLException {
        String query = "UPDATE account SET balance = balance - ? WHERE id = ?";
        try (Connection connection = DatabaseHelper.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setLong(2, accountId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
