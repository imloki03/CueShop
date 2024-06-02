package com.cueshop.service;

import com.cueshop.model.Order;
import com.cueshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Transactional
    public void addNewOrder(Order order){
        try {
            orderRepository.save(order);
        } catch (RuntimeException e){
            throw new RuntimeException("Mua hàng không thành công!");
        }
    }

}
