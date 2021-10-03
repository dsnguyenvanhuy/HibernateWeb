package com.project.controller.admin;

import com.project.pojo.Products;
import com.project.service.IProductService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductManagerController", urlPatterns = {"/admin-product"})
public class ProductManagerController extends HttpServlet {
    @Inject
    private IProductService productService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        List<Products> list = productService.getAllProducts();
        request.setAttribute("listP",list);
        request.getRequestDispatcher("/views/admin/productmanager.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        List<Products> list = new ArrayList<>();

        if (action.equals("filter")){
            String type = request.getParameter("type");
            String method = request.getParameter("method");
            list = productService.getAllProductSorted(type,method);

        }else if (action.equals("search")){
            String search = request.getParameter("search");
            list = productService.getProductsBySearch(search);
            request.setAttribute("search",search);

        }else if (action.equals("add") || action.equals("edit")){
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double saleprice = Double.parseDouble(request.getParameter("saleprice"));
            double importprice = Double.parseDouble(request.getParameter("importprice"));
            String image = request.getParameter("image");
            String type = request.getParameter("type");
            String brand = request.getParameter("brand");
            String status = request.getParameter("status");
            Products product = new Products(name,description,saleprice,image,type,brand,importprice);
            if (status != null){
                product.setStatus(true);
            }else {
                product.setStatus(false);
            }
            String id = request.getParameter("id");
            int result = 0;
            String mess = "";
            if (id == null){// add new Product
                result = productService.addProduct(product);
                if (result == 1){
                    mess = "add product successfully!";
                }else {
                    mess = "add product failed";
                }
            }else {// update Product
                product.setId(Integer.parseInt(id));
                result = productService.updateProduct(product);
                if (result == 1){
                    mess = "update successfully !";
                }else {
                    mess = "update failed !";
                }
            }
            request.setAttribute("result",result);
            request.setAttribute("mess",mess);
            list = productService.getAllProducts();
        }
        request.setAttribute("listP",list);
        request.getRequestDispatcher("/views/admin/productmanager.jsp").forward(request,response);
    }
}
