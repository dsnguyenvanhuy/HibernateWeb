package com.project.service;

import com.project.pojo.Account;
import com.project.pojo.Orders;
import com.project.pojo.Products;

import java.util.List;

public interface IOrderService {
    int addOrder(Orders order, List<Products> productsList);
    List<Orders> getAllOrderToday();
    double getRevenueToday();
    double getCostToday();
    List<Orders> getAllOrderByDate(String from, String to);
    double getRevenueByDate(String from, String to);
    double getCostByDate(String from, String to);
    List<Products> get5BestProductsByDate(String from, String to);
    List<Orders> getAllOrdersByAccount(Account account);
    List<Products> getProductByOrderID(int orderId);
}
