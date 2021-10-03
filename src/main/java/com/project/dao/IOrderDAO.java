package com.project.dao;

import com.project.pojo.Account;
import com.project.pojo.Orders;
import com.project.pojo.Products;

import java.util.List;

public interface IOrderDAO {
    int addOrder(Orders order, List<Products> productsList);
    List<Orders> getAllOrderToday();
    double getValueOfOrder(Orders orders);
    double getCostOfOrder(Orders orders);
    List<Orders> getAllOrderByDate(String from, String to);
    List<Products> get5BestProductsByDate(String from, String to);
    List<Orders> getAllOrdersByAccount(Account account);
    List<Products> getProductByOrderID(int orderId);
}
