package com.project.dao.impl;

import com.project.dao.IAccountDAO;
import com.project.pojo.Account;
import com.project.pojo.SubcribleEmail;
import com.project.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

public class AccountDAO implements IAccountDAO {

    @Override
    public Account findAccountByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Account account = session.find(Account.class,email);
        session.close();
        return account;
    }

    @Override
    public Account addNewAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            account.setPassword(BCrypt.hashpw(account.getPassword(),BCrypt.gensalt(12)));// encoding password
            session.save(account);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return account;
    }

}
