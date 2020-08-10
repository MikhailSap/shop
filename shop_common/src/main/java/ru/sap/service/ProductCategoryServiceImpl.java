package ru.sap.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sap.database.model.ProductCategory;
import ru.sap.database.repo.ProductCategoryRepo;

import java.util.List;


@Component
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ProductCategoryRepo productCatigoryRepo;

    public ProductCategoryServiceImpl(ProductCategoryRepo productCatigoryRepo) {
        this.productCatigoryRepo = productCatigoryRepo;
    }

    @Override
    @Transactional
    public void save(ProductCategory category) {
        productCatigoryRepo.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductCategory getCategoryById(Long id) {
        return productCatigoryRepo.findById(id).get();
    }

    @Override
    @Transactional
    public List<ProductCategory> findAll() {
        return productCatigoryRepo.findAll();
    }

    @Override
    public void deleteCategoryById(Long id) {
        productCatigoryRepo.delete(getCategoryById(id));
    }
}
