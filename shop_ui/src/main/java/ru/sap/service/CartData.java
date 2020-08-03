package ru.sap.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartData implements Serializable {
    @JsonIgnore
    List<LineItem> lineItems;
    @JsonIgnore
    List<Integer> qtys;

    public CartData(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        qtys = new ArrayList<>();
        for (LineItem lineItem : lineItems) {
            qtys.add(lineItem.getQuantity());
        }
    }
}
