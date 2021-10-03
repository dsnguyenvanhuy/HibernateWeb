package com.project.dao.impl;

import com.project.dao.ISubcribeDAO;
import com.project.pojo.SubcribleEmail;
import com.project.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class SubcribeDAO implements ISubcribeDAO {
    @Override
    public int addSubcribleEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SubcribleEmail subcribleEmail = new SubcribleEmail(email);
        try {
            session.getTransaction().begin();
            session.save(subcribleEmail);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return -1;
    }

    @Override
    public boolean subcribeEmailExist(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from SubcribleEmail where email = '"+email.trim()+"'");
            Object object = query.getSingleResult();
            if (object != null){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
}
