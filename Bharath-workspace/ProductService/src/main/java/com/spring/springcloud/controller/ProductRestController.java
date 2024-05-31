package com.spring.springcloud.controller;

import com.spring.springcloud.Coupon;
import com.spring.springcloud.model.Product;
import com.spring.springcloud.repos.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    private final ProductRepository repo;
    private final RestTemplate restTemplate;

    public ProductRestController(ProductRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    @Value("${coupon.service.url}")
    private String couponServiceURL;

    @PostMapping("/product")
    public Product create(@RequestBody Product product) {
        Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return repo.findById(id).get();
    }
}
