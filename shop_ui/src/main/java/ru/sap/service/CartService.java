package ru.sap.service;


import ru.sap.shop_common.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    List<LineItem> getLineItems();
    void addLineItem(ProductDTO product);
    void removeLineItem(Integer id);
    //void setQty(Integer lineItemId, Integer qty);
    void setQty(List<Integer> qtys);
    BigDecimal getTotal();
}
