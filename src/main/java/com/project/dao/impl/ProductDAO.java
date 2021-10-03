package com.project.dao.impl;

import com.project.dao.IProductDAO;
import com.project.model.Brand;
import com.project.pojo.Products;
import com.project.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    @Override
    public List<Products> getAllProductSelling() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products where status = true");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public Products findOneProductById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Products products = session.find(Products.class,id);
        session.close();
        return products;
    }

    @Override
    public List<Products> get4RelatedProducts(Products product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql  = "from Products where type = '"+product.getType()+"' or brand = '"+product.getBrand()+"' and status = true";
        Query query = session.createQuery(sql);
        query.setMaxResults(4);
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Products> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Products> getAllProductSorted(String type, String method) {
        if (type.equals("id")){
            if (method.equals("asc")){
                return getAllProductsByIDAndSortedAsc();
            }else {
                return getAllProductsByIDAndSortedDesc();
            }
        }else if (type.equals("name")){
            if (method.equals("asc")){
                return getAllProductsByNameAndSortedAsc();
            }else {
                return getAllProductsByNameAndSortedDesc();
            }
        }else if (type.equals("saleprice")){
            if (method.equals("asc")){
                return getAllProductsBySalepriceAndSortedAsc();
            }else {
                return getAllProductsBySalepriceAndSortedDesc();
            }
        }else if (type.equals("importprice")){
            if (method.equals("asc")){
                return getAllProductsByImportpriceAndSortedAsc();
            }else {
                return getAllProductsByImportpriceAndSortedDesc();
            }
        }else if (type.equals("brand")){
            if (method.equals("asc")){
                return getAllProductsByBrandAndSortedAsc();
            }else {
                return getAllProductsByBrandAndSortedDesc();
            }
        }else if (type.equals("date")){
            if (method.equals("asc")){
                return getAllProductsByDateAndSortedAsc();
            }else {
                return getAllProductsByDateAndSortedDesc();
            }
        }else if (type.equals("status")){
            if (method.equals("asc")){
                return getAllProductsByStatusAndSortedAsc();
            }else {
                return getAllProductsByStatusAndSortedDesc();
            }
        }
        return null;
    }

    @Override
    public List<Products> getProductsBySearch(String search) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products where name like '%"+search+"%' or brand like '%"+search+"%'");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public int endPage(int numbersOfPage) {
        int numberOfProductSelling = getAllProductSelling().size();
        int endPage = numberOfProductSelling/numbersOfPage;
        if (numberOfProductSelling % numbersOfPage != 0){
            endPage++;
        }
        return endPage;
    }

    @Override
    public int addProduct(Products product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            product.setImportDate(new Date(System.currentTimeMillis()));
            session.save(product);
            session.getTransaction().commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return -1;
    }

    @Override
    public List<Products> getTop3NewProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by id desc ");
        query.setMaxResults(3);
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Brand> getAllBrand() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select p.brand, count (p.brand) from Products p group by p.brand");
        List<Object[]> objectList = query.getResultList();
        List<Brand> list = new ArrayList<>();
        // convert Object to Brand
        for (Object[] objects : objectList) {
            Brand brand = new Brand( (String) objects[0] ,(long) objects[1]);
            list.add(brand);
        }
        session.close();
        return list;
    }

    @Override
    public List<Products> pagingProduct(int index, int numberProduct) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products where status = true order by id asc");
        query.setFirstResult(index-1);
        query.setMaxResults(numberProduct);
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Products> findAllProductBySearch(String category, String search) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "";
        if (category.equals("all")){
            sql = "from Products where name like '%"+search.trim()+"%' and status = true ";
        }else {
            sql = "from Products where name like '%"+search.trim()+"%' and type = '"+category+"' and status = true ";
        }
        Query query = session.createQuery(sql);
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public int updateProduct(Products product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Products oldProduct = session.get(Products.class,product.getId());
            product.setImportDate(oldProduct.getImportDate());
            product.setNumber(oldProduct.getNumber());
            session.merge(product);
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
    public List<Products> getProductByNavigation(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products where type = :type");
        query.setParameter("type",type);
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByIDAndSortedAsc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by id asc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByIDAndSortedDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by id desc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByNameAndSortedAsc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by name asc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByNameAndSortedDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by name desc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsBySalepriceAndSortedAsc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by price asc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsBySalepriceAndSortedDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by price desc");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByImportpriceAndSortedAsc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by importPrice asc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByImportpriceAndSortedDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by importPrice desc");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByBrandAndSortedAsc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by brand asc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByBrandAndSortedDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by brand desc");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByDateAndSortedAsc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by importDate asc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByDateAndSortedDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by importDate desc");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByStatusAndSortedAsc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by status asc ");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }

    public List<Products> getAllProductsByStatusAndSortedDesc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Products order by status desc");
        List<Products> list = query.getResultList();
        session.close();
        return list;
    }
}
