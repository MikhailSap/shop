package ru.sap.service;


import ru.sap.shop_common.dto.ProductDTO;

import java.util.List;

public interface CartService {

    List<LineItem> getLineItems();
    void addLineItem(ProductDTO product);
    void removeLineItem(Integer id);
    void setQty(Integer lineItemId, Integer qty);
}
