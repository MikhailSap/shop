package ru.sap.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sap.database.model.Product;
import ru.sap.database.repo.ProductRepo;
import ru.sap.util.ProductSpecification;


@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> filter(Integer min, Integer max, String partOfName, Pageable pageable) {
        Specification specification = ProductSpecification.trueLiteral();

        if (min != null) {
            specification = specification.and(ProductSpecification.priceGreaterThan(min));
        }
        if (max != null) {
            specification = specification.and(ProductSpecification.priceLessThan(max));
        }
        if (partOfName != null && !partOfName.isEmpty()) {
            specification = specification.and(ProductSpecification.nameLike(partOfName));
        }
        return productRepo.findAll(specification, pageable);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.delete(getProductById(id));
    }
}
