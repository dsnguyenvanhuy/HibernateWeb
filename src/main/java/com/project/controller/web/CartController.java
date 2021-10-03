package com.project.controller.web;

import com.project.model.Cart;
import com.project.pojo.Products;
import com.project.service.IProductService;
import com.project.utils.SessionUtil;

import javax.enterprise.inject.spi.Producer;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    @Inject
    private IProductService productService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Integer id  = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        if (action.equals("add")){
            addToCart(request,response,id,1);
        }else if (action.equals("del")){//
            Cart cart = (Cart) SessionUtil.getInstance().getValue(request,"cart");
            cart.remove(id);
            if (cart.getItems().size() == 0){
                SessionUtil.getInstance().removeValue(request,"cart");
            }
            response.sendRedirect("trang-chu");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Integer id  = Integer.parseInt(request.getParameter("id"));
        Integer qty = Integer.parseInt(request.getParameter("qty"));
        addToCart(request,response,id,qty);
    }
    // method add product into cart
    public void addToCart(HttpServletRequest request,HttpServletResponse response,int Id, int amountProduct)throws ServletException, IOException{
        Cart cart = (Cart) SessionUtil.getInstance().getValue(request,"cart");
        if (cart == null){
            cart = new Cart();
        }
        Products product = productService.findOneProductById(Id);
        cart.add(product,amountProduct);
        SessionUtil.getInstance().putValue(request,"cart",cart);
        response.sendRedirect("trang-chu");
    }
}
