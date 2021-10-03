package com.project.controller.web;

import com.project.pojo.Products;
import com.project.service.IProductService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NavigationCotroller", value = "/navigation")
public class NavigationCotroller extends HttpServlet {
    @Inject
    private IProductService productService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        String navigation = request.getParameter("nav");
            List<Products> list = productService.getProductByNavigation(navigation);
            request.setAttribute("nav",navigation);
            request.setAttribute("listP",list);
            request.getRequestDispatcher("/views/web/home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
