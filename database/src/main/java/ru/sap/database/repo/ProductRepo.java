package ru.sap.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sap.database.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
   // Page<Product> findAll(Specification<Product> specification, Pageable pageable);
//    Page<Product> findByPriceLessThan(Integer max, Pageable pageable);
//    Page<Product> findByPriceGreaterThan(Integer min, Pageable pageable);
//    Page<Product> findByPriceBetween(Integer min, Integer max, Pageable pageable);
//
//    Page<Product> findByNameLike(String partOfName, Pageable pageable);
}
