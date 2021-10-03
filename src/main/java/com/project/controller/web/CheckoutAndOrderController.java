package com.project.controller.web;

import com.project.model.Cart;
import com.project.pojo.Orders;
import com.project.service.IOrderService;
import com.project.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet(name = "CheckoutAndOrderController", urlPatterns = {"/checkout","/order"})
public class CheckoutAndOrderController extends HttpServlet {
    @Inject
    private IOrderService orderService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf8");
        request.getRequestDispatcher("/views/web/checkout.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String address = request.getParameter("address") +","+ request.getParameter("city") +","+ request.getParameter("country");
        String zipcode = request.getParameter("zipcode");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        Cart cart = (Cart) SessionUtil.getInstance().getValue(request,"cart");
        Orders order = new Orders(email,0,Timestamp.valueOf(LocalDateTime.now()),"",address,phone,fullname,note);
        order.setTotals((float) cart.getAmount());
        int result = orderService.addOrder(order,cart.getItems());
        String mess = "";
        if (result == 1){
            mess = "Order successfully !";
            SessionUtil.getInstance().removeValue(request,"cart");
        }else {
            mess = "Order failed . Please again !";
        }
        request.setAttribute("mess",mess);
        request.setAttribute("rs",result);
        request.getRequestDispatcher("/views/web/anouncement.jsp").forward(request,response);
    }
}
