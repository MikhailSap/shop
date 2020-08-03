package ru.sap.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.sap.shop_common.dto.ProductDTO;

import java.math.BigDecimal;
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

//    @Override
//    public void setQty(Integer lineItemId, Integer qty) {
//        lineItems.get(lineItemId).setQuantity(qty);
//    }

    @Override
    public void setQty(List<Integer> qtys) {
        for (int i = 0; i < lineItems.size(); i++) {
            lineItems.get(i).setQuantity(qtys.get(i));
        }
    }

    public void addLineItem(ProductDTO product) {
        LineItem lineItem = new LineItem(lineItems.size(), product, 1);
        lineItems.add(lineItem);
        for (LineItem lineItem1 : lineItems) {
            System.out.println(lineItem.getProduct().getName()+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
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

    @JsonIgnore
    public BigDecimal getTotal() {
        return lineItems.stream()
                 .map(LineItem::getSubTotal)
                 .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
