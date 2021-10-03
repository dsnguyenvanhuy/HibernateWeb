package com.project.dao.impl;

import com.project.dao.ICategoryDAO;
import com.project.model.Categories;
import com.project.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    @Override
    public List<Categories> getAllCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select p.type, count (p.type) from Products p group by p.type");
        List<Object[]> objectlist = query.getResultList();
        session.close();
        // convert Object to Categories
        List<Categories> list = new ArrayList<>();
        for (Object[] objects : objectlist) {
            Categories category = new Categories((String)objects[0],(long)objects[1]);
            list.add(category);
        }
        return list;
    }
}
