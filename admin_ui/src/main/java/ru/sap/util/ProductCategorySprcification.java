package ru.sap.util;

import org.springframework.data.jpa.domain.Specification;
import ru.sap.database.model.Product;
import ru.sap.database.model.ProductCategory;

public class ProductCategorySprcification {
    public static Specification<ProductCategory> trueLiteral() {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

    }

    public static Specification<ProductCategory> nameLike(String partOfName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+partOfName+"%"));

    }
}
