package com.example.banking.service;

import com.example.banking.dao.AccountDAO;
import com.example.banking.entity.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
    }

    public List<Account> getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
    }

    public Account getAccountById(Long id) throws SQLException {
        return accountDAO.getAccountById(id);
    }
}
