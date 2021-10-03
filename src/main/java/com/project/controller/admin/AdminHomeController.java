package com.project.controller.admin;

import com.project.pojo.Orders;
import com.project.pojo.Products;
import com.project.service.IOrderService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminHomeController", urlPatterns = {"/admin-home","/admin-filter"})
public class AdminHomeController extends HttpServlet {
    @Inject
    private IOrderService orderService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        todayResult(request);
        request.getRequestDispatcher("/views/admin/home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        todayResult(request);
        String from  = request.getParameter("from");
        String to  = request.getParameter("to");
        if (to.compareTo(from) >= 0){
            List<Orders> ordersList = orderService.getAllOrderByDate(from,to);
            int orderNumbers = ordersList.size();
            double revenue = orderService.getRevenueByDate(from,to);
            double cost = orderService.getCostByDate(from,to);
            double profit = revenue - cost;
            List<Products> productsList = orderService.get5BestProductsByDate(from,to);
            request.setAttribute("fromF",from);
            request.setAttribute("toF",to);
            request.setAttribute("orderNumbersF",orderNumbers);
            request.setAttribute("listO",ordersList);
            request.setAttribute("listP",productsList);
            request.setAttribute("revenueF",Math.ceil(revenue * 100.00)/100.00);
            request.setAttribute("costF",Math.ceil(cost * 100.00)/100.00);
            request.setAttribute("profitF", Math.ceil(profit * 100.00) / 100.00);
        }else {
            String mess = "Start day must be greater than or equal End day";
            request.setAttribute("mess",mess);
        }
        request.getRequestDispatcher("/views/admin/home.jsp").forward(request,response);
    }

    public void todayResult(HttpServletRequest request){
        List<Orders> list = orderService.getAllOrderToday();
        double revenueToday = orderService.getRevenueToday();
        double costToday = orderService.getCostToday();
        double profitToday = revenueToday - costToday;
        request.setAttribute("orderNumbers",list.size());
        request.setAttribute("revenue",Math.ceil(revenueToday * 100.00)/100.00);
        request.setAttribute("cost",Math.ceil(costToday * 100.00)/100.00);
        request.setAttribute("profit", Math.ceil(profitToday * 100.00) / 100.00);
    }
}
