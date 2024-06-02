package com.cueshop.controller;

import com.cueshop.model.Order;
import com.cueshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/add_new")
    public ResponseEntity<String> addNewOrder(@RequestBody Order order){
        try {
            orderService.addNewOrder(order);
            return ResponseEntity.ok("Đặt hàng thành công!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
