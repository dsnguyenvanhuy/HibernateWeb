package com.project.controller.admin;

import com.project.pojo.Products;
import com.project.service.IProductService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Iterator;
@MultipartConfig
@WebServlet(name = "ImportAndExportFileController", urlPatterns = {"/admin-import","/admin-export"})
public class ImportAndExportFileController extends HttpServlet {
    @Inject
    private IProductService productService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Productlist.xlsx");
        Workbook workbook = new XSSFWorkbook();
        productService.writeFileExcel(workbook);
        //excute write file Excell and download into PC
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        Part part = request.getPart("file");
        InputStream excelFile = part.getInputStream();
        XSSFWorkbook wookbook = new XSSFWorkbook(excelFile);
        productService.addProductFromFileExcell(wookbook);
        // close
        wookbook.close();
        excelFile.close();
        // chuyển  tiếp
        response.sendRedirect("admin-product");
    }
}
