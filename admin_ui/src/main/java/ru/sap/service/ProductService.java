package ru.sap.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sap.database.model.Product;
import ru.sap.dto.ProductDTO;

import java.io.IOException;
import java.util.List;


public interface ProductService {

    void save(ProductDTO product);

    Product getProductById(Long id);

    List<ProductDTO> findAll();

    void deleteProductById(Long id);
}
