package ru.sap.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sap.database.model.Product;


public interface ProductService {

    void save(Product product);

    Product getProductById(Long id);

    Page<Product> filter(Integer min, Integer max, String partOfName, Pageable pageable);

    void deleteProductById(Long id);
}
