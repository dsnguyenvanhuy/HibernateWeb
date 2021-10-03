package com.project.controller.web;

import com.project.pojo.Account;
import com.project.pojo.Orders;
import com.project.pojo.Products;
import com.project.service.IOrderService;
import com.project.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyAccountController", value = "/myaccount")
public class MyAccountController extends HttpServlet {
    @Inject
    private IOrderService orderService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        Account account = (Account) SessionUtil.getInstance().getValue(request,"acc");
        String oid = request.getParameter("oid");
        if (oid == null){
            List<Orders> list = orderService.getAllOrdersByAccount(account);
            request.setAttribute("listO",list);
        }else {
            List<Products> productsList = orderService.getProductByOrderID(Integer.parseInt(oid));
            request.setAttribute("listP",productsList);
        }
        request.getRequestDispatcher("/views/web/myaccount.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
