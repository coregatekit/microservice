package com.example.ProductService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "products")
public class ProductController {
    @Autowired
    private final RestTemplate restTemplate;

    @Bean
    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/search")
    public Product getProductsByTypeAndName(@RequestParam(value = "sku") final String sku) {
        String url = "http://PRICING-SERVICE/products/price?sku=" + sku;
        return restTemplate.getForObject(url, Product.class);
    }
}

@Data
class Product {
    private String sku;
    private String price;
}