package com.project.service;

import com.project.pojo.Account;

public interface IAccountService {
    Account checkLogin(String email, String password);
    boolean existEmail(String email);
    Account addNewAccount(Account account);
}
