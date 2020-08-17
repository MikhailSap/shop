package ru.sap;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.sap.dto.ProductDTO;
import ru.sap.service.ProductService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ProductService productService;


    @Test
    public void testAddProduct() throws Exception {
        String testName = "Product_name_test";
        ProductDTO expectedProduct = new ProductDTO();
        expectedProduct.setName(testName);
        expectedProduct.setPrice(123);
        mvc.perform(post("/addProduct")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .requestAttr("product", expectedProduct))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/newProduct"));

        Long productId = productService.getProductByName(testName).getId();
        ProductDTO currentProduct = productService.getProductById(productId);

        assertEquals(expectedProduct, currentProduct);
    }





}
