package com.cueshop.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    public Order() {
    }

    public Order(Long id, String username, String address, List<OrderLine> orderList) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.orderList = orderList;
        this.payment_state = false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPayment_state() {
        return payment_state;
    }

    public void setPayment_state(Boolean payment_state) {
        this.payment_state = payment_state;
    }

    public List<OrderLine> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderLine> orderList) {
        this.orderList = orderList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}