package com.rates.ratesproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppConfigTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRestTemplateBean(){
        assertNotNull(restTemplate, "Rest template bean should not be null!");
    }

    @Test
    public void testObjectMapperBean(){
        assertNotNull(objectMapper, "Object mapper bean should not be null!");
    }
}
