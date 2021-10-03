package com.project.dao.impl;

import com.project.dao.IOrderDAO;
import com.project.pojo.Account;
import com.project.pojo.Orders;
import com.project.pojo.OrdersDetail;
import com.project.pojo.Products;
import com.project.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    @Override
    public int addOrder(Orders order, List<Products> productsList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            // add Order into databse
             int order_id = (int) session.save(order);
            // add Order_detail into database
            for (Products products : productsList) {
                OrdersDetail ordersDetail = new OrdersDetail();
                ordersDetail.setOrderId(order_id);
                ordersDetail.setProductId(products.getId());
                ordersDetail.setAmountProduct(products.getNumber());
                ordersDetail.setPriceProduct(products.getPrice());
                session.save(ordersDetail);
            }
            session.getTransaction().commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();;
        }finally {
            session.close();
        }
        return -1;
    }

    @Override
    public List<Orders> getAllOrderToday() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Date today = Date.valueOf(LocalDate.now());
        Query query = session.createQuery("from Orders where orderTime between '"+today+" 00:00:00' and '"+today+" 23:59:59'");
        List<Orders> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public double getValueOfOrder(Orders orders) {
        double value = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql  = "from OrdersDetail where orderId = "+orders.getOrderId();
        Query query = session.createQuery(sql);
        List<OrdersDetail> list = query.getResultList();
        // calculate value
        for (OrdersDetail ordersDetail : list) {
            value += ordersDetail.getAmountProduct() * ordersDetail.getPriceProduct();
        }
        session.close();
        return value;
    }

    @Override
    public double getCostOfOrder(Orders orders) {
        double cost = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql  = "from OrdersDetail where orderId = "+orders.getOrderId();
        Query query = session.createQuery(sql);
        List<OrdersDetail> list = query.getResultList();
        // calculate cost
        for (OrdersDetail ordersDetail : list) {
            Products products = session.get(Products.class,ordersDetail.getProductId());
            cost += products.getImportPrice() * ordersDetail.getAmountProduct();
        }
        session.close();
        return cost;
    }

    @Override
    public List<Orders> getAllOrderByDate(String from, String to) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Orders where orderTime between '"+from+" 00:00:00' and '"+to+" 23:59:59'");
        List<Orders> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Products> get5BestProductsByDate(String from, String to) {
        Timestamp from1 = Timestamp.valueOf(from+" 00:00:00");
        Timestamp to1 = Timestamp.valueOf(to+" 23:59:59");
        Session session = HibernateUtil.getSessionFactory().openSession();
        // get 5 best product_id and amount by date
        StringBuilder sql = new StringBuilder("select d.productId, sum(d.amountProduct) from OrdersDetail d inner join Orders o");
        sql.append(" on d.orderId = o.orderId where o.orderTime between :from and :to");
        sql.append(" group by d.productId order by sum(d.amountProduct) desc");
        Query query = session.createQuery(sql.toString());
        query.setMaxResults(5);
        query.setParameter("from",from1);
        query.setParameter("to",to1);
        List<Object[]> productIdList = query.getResultList();
        // get product List
        ProductDAO productDAO = new ProductDAO();
        List<Products> productsList = new ArrayList<Products>();
        for (Object[] objects : productIdList) {
            Products products = productDAO.findOneProductById((Integer) objects[0]);
            productsList.add(products);
        }
        session.close();
        return productsList;
    }

    @Override
    public List<Orders> getAllOrdersByAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Orders where userMail = :mail");
        query.setParameter("mail",account.getMail());
        List<Orders> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Products> getProductByOrderID(int orderId) {
        List<Products> productsList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        // get list product_id of order
        Query query = session.createQuery("select productId from OrdersDetail where orderId = :orderid");
        query.setParameter("orderid",orderId);
        List<Integer> idList = query.getResultList();
        // add product into productlist
        ProductDAO productDAO = new ProductDAO();
        for (Integer id : idList) {
            Products products = productDAO.findOneProductById(id);
            productsList.add(products);
        }
        session.close();
        return productsList;
    }

}
