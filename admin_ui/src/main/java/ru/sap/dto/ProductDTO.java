package ru.sap.dto;

import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ru.sap.database.model.Product;
import ru.sap.database.model.ProductCategory;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Getter
@Setter
public class ProductDTO {
        private Long id;
        private String name;
        private String description;
        private int price;
        private String picture;
        private List<PictureDTO> pictures;
        private MultipartFile[] file;
        private List<ProductCategory> categories;

        public ProductDTO() {
        }

        public ProductDTO(Product product) {
                this.id = product.getId();
                this.name = product.getName();
                this.description = product.getDescription();
                this.price = product.getPrice();
                this.picture = product.getPicture();
                this.pictures = product.getPictures()
                                       .stream()
                                       .map(PictureDTO::new)
                                       .collect(Collectors.toList());
                this.categories = product.getCategories();
        }
}
