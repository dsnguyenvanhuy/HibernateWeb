package com.project.dao;

import com.project.model.Brand;
import com.project.pojo.Products;

import java.util.List;

public interface IProductDAO {
    List<Products> getAllProductSelling();
    Products findOneProductById(int id);
    List<Products> get4RelatedProducts(Products product);
    List<Products> getAllProducts();
    List<Products> getAllProductSorted(String type,String method);
    List<Products> getProductsBySearch(String search);
    int endPage(int numbersOfPage);
    int addProduct(Products product);
    List<Products> getTop3NewProducts();
    List<Brand> getAllBrand();
    List<Products> pagingProduct(int index, int numberProduct);
    List<Products> findAllProductBySearch(String category,String search);
    int updateProduct(Products product);
    List<Products> getProductByNavigation(String type);
}
