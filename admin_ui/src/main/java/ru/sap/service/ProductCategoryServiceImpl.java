package ru.sap.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sap.database.model.Product;
import ru.sap.database.model.ProductCategory;
import ru.sap.database.repo.ProductCategoryRepo;
import ru.sap.util.ProductSpecification;

import java.util.List;


@Component
public class ProductCategoryServiceImpl implements ProductCategoryService{
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
    @Transactional(readOnly = true)
    public Page<ProductCategory> filter(String partOfName, Pageable pageable) {
        Specification specification = ProductSpecification.trueLiteral();

        if (partOfName != null && !partOfName.isEmpty()) {
            specification = specification.and(ProductSpecification.nameLike(partOfName));
        }
        return productCatigoryRepo.findAll(specification, pageable);
    }

    @Override
    public void deleteCategoryById(Long id) {
        productCatigoryRepo.delete(getCategoryById(id));
    }
}
