package ru.sap.shop_common.service;

import ru.sap.database.model.Product;
import ru.sap.shop_common.dto.ProductDTO;

import java.util.List;


public interface ProductService {

    void save(ProductDTO product);

    ProductDTO getProductById(Long id);

    List<ProductDTO> findAll();

    void deleteProductById(Long id);
}
