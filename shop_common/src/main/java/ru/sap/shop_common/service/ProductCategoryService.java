package ru.sap.shop_common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sap.database.model.Product;
import ru.sap.database.model.ProductCategory;

import java.util.List;


public interface ProductCategoryService {
    void save(ProductCategory product);

    ProductCategory getCategoryById(Long id);

    List<ProductCategory> findAll();

    void deleteCategoryById(Long id);
}
