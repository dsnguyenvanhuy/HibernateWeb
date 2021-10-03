package com.project.dao;

import com.project.pojo.Account;

public interface IAccountDAO {
    Account findAccountByEmail(String email);
    Account addNewAccount(Account account);
}
