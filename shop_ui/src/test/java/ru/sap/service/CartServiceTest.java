package ru.sap.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sap.shop_common.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartServiceTest {
    private CartService cartService;


    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testCartServiceStateAfterInit() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
    }


    @Test
    public void testAddLineItem() {
        ProductDTO expectedProduct = new ProductDTO();
        expectedProduct.setName("Product name");
        expectedProduct.setPrice(123);

        cartService.addLineItem(expectedProduct);

        assertEquals(1, cartService.getLineItems().size());

        ProductDTO currentProduct = cartService.getLineItems().get(0).getProduct();
        assertEquals(expectedProduct.getName(), currentProduct.getName());
        assertEquals(expectedProduct.getPrice(), currentProduct.getPrice());
    }

    @Test
    public void testRemoveLineItem() {
        int countProductsAfterRemove = 2;
        int idLineItemForRemove = 1;

        prepareLineItems();

        cartService.removeLineItem(idLineItemForRemove);

        assertEquals(countProductsAfterRemove, cartService.getLineItems().size());

        List<LineItem> lineItems = cartService.getLineItems();
        String[] expectedNames = {"Name_0", "Name_2"};

        for (int i = 0; i < lineItems.size(); i++) {
            assertEquals(expectedNames[i], lineItems.get(i).getProduct().getName());
        }
    }


    @Test
    public void testSetQty() {
        int qtyOne = 1;
        int qtyTwo = 5;
        int qtyThree = 175;

        List<Integer> qtys = new ArrayList<>();
        qtys.add(qtyOne);
        qtys.add(qtyTwo);
        qtys.add(qtyThree);

        prepareLineItems();

        cartService.setQty(qtys);

        assertEquals(qtyOne, cartService.getLineItems().get(0).getQuantity());
        assertEquals(qtyTwo, cartService.getLineItems().get(1).getQuantity());
        assertEquals(qtyThree, cartService.getLineItems().get(2).getQuantity());
    }

    @Test
    public void testGetTotal() {
        prepareLineItems();
        int[] prices = {678, 15, 93};

        List<LineItem> lineItems = cartService.getLineItems();

        for (int i = 0; i < lineItems.size(); i++) {
            lineItems.get(i).getProduct().setPrice(prices[i]);
        }

        BigDecimal expectedTotal = new BigDecimal(0);
        for (int price : prices) {
            expectedTotal = expectedTotal.add(new BigDecimal(price));
        }

        assertEquals(expectedTotal, cartService.getTotal());
    }

    private void prepareLineItems() {
        int countProductsForTest = 3;

        for (int i = 0; i < countProductsForTest; i++) {
            ProductDTO product = new ProductDTO();
            product.setName("Name_" + i);
            cartService.addLineItem(product);
        }

        assertEquals(countProductsForTest, cartService.getLineItems().size());
    }
}



