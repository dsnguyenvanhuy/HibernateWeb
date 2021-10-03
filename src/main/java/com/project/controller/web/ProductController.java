package com.project.controller.web;

import com.project.pojo.Products;
import com.project.service.IProductService;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product")
public class ProductController extends HttpServlet {
    @Inject
    private IProductService productService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf8");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Products product = new Products();
        product = productService.findOneProductById(id);
        request.setAttribute("p",product);
        List<Products> list = productService.get4RelatedProducts(product);
        request.setAttribute("listR",list);
        request.getRequestDispatcher("/views/web/product.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
