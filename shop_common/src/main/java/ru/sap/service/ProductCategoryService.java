package ru.sap.service;

import ru.sap.database.model.ProductCategory;

import java.util.List;


public interface ProductCategoryService {
    void save(ProductCategory product);

    ProductCategory getCategoryById(Long id);

    List<ProductCategory> findAll();

    void deleteCategoryById(Long id);
}
