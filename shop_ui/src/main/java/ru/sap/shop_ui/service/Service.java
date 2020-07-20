package ru.sap.shop_ui.service;

import org.springframework.stereotype.Component;
import ru.sap.database.model.Product;
import ru.sap.database.repo.ProductRepo;

import java.util.List;

@Component
public class Service {
    private ProductRepo productRepo;

    public Product getProduct(Long id) {
        return productRepo.findById(id).get();
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
