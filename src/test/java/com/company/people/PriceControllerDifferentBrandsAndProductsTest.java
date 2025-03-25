package com.company.people;

import com.company.people.entity.Price;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerDifferentBrandsAndProductsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     
    @Test
    void testDifferentBrandSameProduct() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        int productId = 35455;
        int brandId = 1;

        // Test para marca 1 (ZARA)";
        MvcResult resultBrand1 = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price responseBrand1 = objectMapper.readValue(resultBrand1.getResponse().getContentAsString(), Price.class);
        assertEquals(35.50, responseBrand1.getPrice(), 0.001);
        assertEquals(1, responseBrand1.getBrandId());

        // Test para marca 2 (OTRA_MARCA)
        
        productId = 99999;
        brandId = 2;
        
        MvcResult resultBrand2 = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price responseBrand2 = objectMapper.readValue(resultBrand2.getResponse().getContentAsString(), Price.class);
        assertEquals(90.00, responseBrand2.getPrice(), 0.001);
        assertEquals(2, responseBrand2.getBrandId());
    }

    @Test
    void testDifferentProductSameBrand() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        int brandId = 1;
        int productId = 35455;
        // Test para producto 35455 (ZARA)";
        MvcResult resultProduct1 = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price responseProduct1 = objectMapper.readValue(resultProduct1.getResponse().getContentAsString(), Price.class);
        assertEquals(35.50, responseProduct1.getPrice(), 0.001);
        assertEquals(35455, responseProduct1.getProductId());

        // Test para producto 99999 (ZARA)
        productId = 99999;
        MvcResult resultProduct2 = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price responseProduct2 = objectMapper.readValue(resultProduct2.getResponse().getContentAsString(), Price.class);
        assertEquals(100.00, responseProduct2.getPrice(), 0.001);
        assertEquals(99999, responseProduct2.getProductId());
    }

    @Test
    void testDifferentBrandAndProduct() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        // Test para marca 2, producto que NO existe (88888)
        int brandId = 2;
        int productId = 88888;
        mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDifferentBrandPromotionTime() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 14:00:00", formatter);
        int brandId = 1;
        int productId = 35455;
        // ZARA no tiene promocion a esta hora
        MvcResult resultBrand1 = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price responseBrand1 = objectMapper.readValue(resultBrand1.getResponse().getContentAsString(), Price.class);
        assertEquals(35.50, responseBrand1.getPrice(), 0.001);

        //Marca 2 tiene promocion a esta hora";
        productId = 35455;
        brandId = 2;
        MvcResult resultBrand2 = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price responseBrand2 = objectMapper.readValue(resultBrand2.getResponse().getContentAsString(), Price.class);
        assertEquals(35.00, responseBrand2.getPrice(), 0.001);
    }
}