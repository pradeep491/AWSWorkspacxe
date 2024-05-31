package com.spring.springcloud.controller;

import com.spring.springcloud.model.Coupon;
import com.spring.springcloud.repos.CouponRepository;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
@AllArgsConstructor
public class CouponRestController {

    private CouponRepository repo;

    @PostMapping("/coupons")
    private Coupon create(@RequestBody Coupon coupon) {
        return repo.save(coupon);
    }

    @GetMapping("/coupons/{code}")
    public Coupon getCoupon(@PathVariable("code") String code) {
        return repo.findByCode(code);
    }
}
