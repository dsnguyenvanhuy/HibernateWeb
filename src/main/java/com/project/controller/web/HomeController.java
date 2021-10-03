package com.project.controller.web;

import com.project.model.Brand;
import com.project.model.Categories;
import com.project.pojo.Products;
import com.project.service.ICategoryService;
import com.project.service.IProductService;
import com.project.service.ISubcribeEmailService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", urlPatterns = {"/trang-chu","/search","/subcrible"})
public class HomeController extends HttpServlet {
    private final int numberOfPage = 6;
    @Inject
    private IProductService productService;
    @Inject
    private ICategoryService categoryService;
    @Inject
    private ISubcribeEmailService subcribeEmailService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexing =request.getParameter("index");
        Integer index ;
        if (indexing == null){
            index = 1;
        }else {
            index = Integer.parseInt(indexing);
        }
        int endPage = productService.endPage(numberOfPage);
        List<Products> list1 = productService.pagingProduct(index,numberOfPage);
        request.setAttribute("tag",index);
        request.setAttribute("endP",endPage);
        request.setAttribute("listP",list1);
        common(response,request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        if (action.equals("sub")){
            String mess = "";
            String email = request.getParameter("email");
            int rs = subcribeEmailService.addSubcribleEmail(email);
            if (rs == 1){
                mess = "subcribe successfully";
            }else if (rs == -1){
                mess = "subcribe failed";
            }
            request.setAttribute("rs",rs);
            request.setAttribute("mess",mess);
            request.getRequestDispatcher("/views/web/anouncement.jsp").forward(request,response);
        }else if (action.equals("search")){
            String category = request.getParameter("category");
            String search = request.getParameter("search");
            List<Products> list = productService.findAllProductBySearch(category,search);
            request.setAttribute("category",category);
            request.setAttribute("listP",list);
            common(response,request);
        }
    }
    public void common(HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        List<Categories> list2 = categoryService.getAllCategories();
        List<Products> list3 = productService.getTop3NewProducts();
        List<Brand> list4 = productService.getAllBrand();
        request.setAttribute("listC",list2);
        request.setAttribute("listN",list3);
        request.setAttribute("listB",list4);
        request.getRequestDispatcher("/views/web/home.jsp").forward(request,response);
    }
}
