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
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PeopleApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Test
    void test1_10amDay14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        int productId = 35455;
        int brandId = 1;

        MvcResult result = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price response = objectMapper.readValue(result.getResponse().getContentAsString(), Price.class);
        assertEquals(35.50, response.getPrice(), 0.001);
        assertEquals(1, response.getPriceList());
    }

    @Test
    void test2_4pmDay14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 16:00:00", formatter);
        int productId = 35455;
        int brandId = 1;

        MvcResult result = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price response = objectMapper.readValue(result.getResponse().getContentAsString(), Price.class);
        assertEquals(25.45, response.getPrice(), 0.001);
        assertEquals(2, response.getPriceList());
    }

    @Test
    void test3_9pmDay14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 21:00:00", formatter);
        int productId = 35455;
        int brandId = 1;

        MvcResult result = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price response = objectMapper.readValue(result.getResponse().getContentAsString(), Price.class);
        assertEquals(35.50, response.getPrice(), 0.001);
        assertEquals(1, response.getPriceList());
    }

    @Test
    void test4_10amDay15() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15 10:00:00", formatter);
        int productId = 35455;
        int brandId = 1;

        MvcResult result = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price response = objectMapper.readValue(result.getResponse().getContentAsString(), Price.class);
        assertEquals(30.50, response.getPrice(), 0.001);
        assertEquals(3, response.getPriceList());
    }

    @Test
    void test5_9pmDay16() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16 21:00:00", formatter);
        int productId = 35455;
        int brandId = 1;

        MvcResult result = mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Price response = objectMapper.readValue(result.getResponse().getContentAsString(), Price.class);
        assertEquals(38.95, response.getPrice(), 0.001);
        assertEquals(4, response.getPriceList());
    }

    @Test
    void testNotFound() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2021-06-14 10:00:00", formatter);
        int productId = 8888;
        int brandId = 1;
        ResultActions result = mockMvc.perform(get("/price")
                .param("applicationDate", applicationDate.toString())
                .param("productId", String.valueOf(productId))
                .param("brandId", String.valueOf(brandId))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }
}