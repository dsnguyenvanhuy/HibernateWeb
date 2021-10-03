package com.project.service.impl;

import com.project.dao.IAccountDAO;
import com.project.pojo.Account;
import com.project.service.IAccountService;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;

public class AccountService implements IAccountService {
    @Inject
    private IAccountDAO accountDAO;
    @Override
    public Account checkLogin(String email, String password) {
        Account account = accountDAO.findAccountByEmail(email);
        if (account != null){
            boolean checkPassword = BCrypt.checkpw(password,account.getPassword());
            if (checkPassword){
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean existEmail(String email) {
        Account account = accountDAO.findAccountByEmail(email);
        if (account != null){
            return true;
        }
        return false;
    }

    @Override
    public Account addNewAccount(Account account) {
        return accountDAO.addNewAccount(account);
    }

}
