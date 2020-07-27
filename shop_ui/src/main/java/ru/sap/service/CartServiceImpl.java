package ru.sap.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.sap.shop_common.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {
    private static final long serialVersionUID = -9025621122549454991L;

    private List<LineItem> lineItems;

    public CartServiceImpl() {
        this.lineItems = new ArrayList<>();
    }

    @Override
    public void setQty(Integer lineItemId, Integer qty) {
        lineItems.get(lineItemId).setQuantity(qty);
    }

    public void addLineItem(ProductDTO product) {
        LineItem lineItem = new LineItem(lineItems.size(), product, 1);
        lineItems.add(lineItem);
    }

    public void removeLineItem(Integer id) {
        List<LineItem> tempList = new ArrayList<>();

        for (LineItem lineItem : lineItems) {
            if (lineItem.getId() != id) {
                lineItem.setId(tempList.size());
                tempList.add(lineItem);
            }
        }
        lineItems = tempList;
    }


    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
