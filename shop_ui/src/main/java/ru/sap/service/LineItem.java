package ru.sap.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sap.shop_common.dto.ProductDTO;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItem implements Serializable {
    private Integer id;
    private ProductDTO product;
    private Integer quantity;

    @JsonIgnore
    public BigDecimal getSubTotal() {
        return BigDecimal.valueOf(product.getPrice() * quantity);
    }
}
