package com.project.service;

import com.project.model.Brand;
import com.project.pojo.Products;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface IProductService {
    List<Products> getAllProductSelling();
    Products findOneProductById(int id);
    List<Products> get4RelatedProducts(Products product);
    List<Products> getAllProducts();
    List<Products> getAllProductSorted(String type,String method);
    List<Products> getProductsBySearch(String search);
    int endPage(int numbersOfPage);
    void writeFileExcel(Workbook workbook);
    int addProduct(Products product);
    void addProductFromFileExcell(XSSFWorkbook wookbook);
    List<Products> getTop3NewProducts();
    List<Brand> getAllBrand();
    List<Products> pagingProduct(int index, int numberProduct);
    List<Products> findAllProductBySearch(String category,String search);
    int updateProduct(Products product);
    List<Products> getProductByNavigation(String type);
}
