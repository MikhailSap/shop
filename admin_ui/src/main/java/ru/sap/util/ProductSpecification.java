package ru.sap.util;

import org.springframework.data.jpa.domain.Specification;
import ru.sap.database.model.Product;


public class ProductSpecification {
    public static Specification<Product> trueLiteral() {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)));

    }

    public static Specification<Product> priceGreaterThan(Integer min) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min));

    }

    public static Specification<Product> priceLessThan(Integer max) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), max));

    }

    public static Specification<Product> nameLike(String partOfName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+partOfName+"%"));

    }
}
