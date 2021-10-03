package com.project.service.impl;

import com.project.dao.ICategoryDAO;
import com.project.model.Categories;
import com.project.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryDAO categoryDAO;
    @Override
    public List<Categories> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
