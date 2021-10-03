package com.project.service.impl;

import com.project.dao.IOrderDAO;
import com.project.pojo.Account;
import com.project.pojo.Orders;
import com.project.pojo.Products;
import com.project.service.IOrderService;

import javax.inject.Inject;
import java.util.List;

public class OrderService implements IOrderService {
    @Inject
    private IOrderDAO orderDAO;
    @Override
    public int addOrder(Orders order, List<Products> productsList) {
        return orderDAO.addOrder(order,productsList);
    }

    @Override
    public List<Orders> getAllOrderToday() {
        return orderDAO.getAllOrderToday();
    }

    @Override
    public double getRevenueToday() {
        double revenue = 0;
        // get all order today
        List<Orders> orderList = orderDAO.getAllOrderToday();
        // calculate value of each order
        for (Orders order : orderList) {
            revenue += orderDAO.getValueOfOrder(order);
        }
        return revenue;
    }

    @Override
    public double getCostToday() {
        double cost = 0;
        // get all order today
        List<Orders> orderList = orderDAO.getAllOrderToday();
        for (Orders orders : orderList) {
            cost += orderDAO.getCostOfOrder(orders);
        }
        return cost;
    }

    @Override
    public List<Orders> getAllOrderByDate(String from, String to) {
        return orderDAO.getAllOrderByDate(from,to);
    }

    @Override
    public double getRevenueByDate(String from, String to) {
        double revenue = 0;
        //get all Order by date
        List<Orders> ordersList = orderDAO.getAllOrderByDate(from,to);
        // calculate revenue
        for (Orders orders : ordersList) {
            revenue += orderDAO.getValueOfOrder(orders);
        }
        return revenue;
    }

    @Override
    public double getCostByDate(String from, String to) {
        double cost = 0;
        //get all Order by date
        List<Orders> ordersList = orderDAO.getAllOrderByDate(from,to);
        // calculate cost
        for (Orders orders : ordersList) {
            cost += orderDAO.getCostOfOrder(orders);
        }
        return cost;
    }

    @Override
    public List<Products> get5BestProductsByDate(String from, String to) {
        return orderDAO.get5BestProductsByDate(from,to);
    }

    @Override
    public List<Orders> getAllOrdersByAccount(Account account) {
        return orderDAO.getAllOrdersByAccount(account);
    }

    @Override
    public List<Products> getProductByOrderID(int orderId) {
        return orderDAO.getProductByOrderID(orderId);
    }
}
