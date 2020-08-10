package ru.sap.service;

import ru.sap.database.model.Product;
import ru.sap.dto.ProductDTO;

import java.util.List;


public interface ProductService {

    void save(ProductDTO product);

    void save(Product product);

    ProductDTO getProductById(Long id);

    Product getProductByName(String name);

    List<ProductDTO> findAll();

    void deleteProductById(Long id);
}
