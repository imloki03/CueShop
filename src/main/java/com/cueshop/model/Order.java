package com.cueshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OrderLine> orderList = new ArrayList<>();

    @Column(name = "payment_state")
    private Boolean payment_state;


    public Order(Long id, String username, String address, List<OrderLine> orderList) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.orderList = orderList;
        this.payment_state = false;
    }
}