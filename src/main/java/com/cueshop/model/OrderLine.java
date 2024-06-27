package com.cueshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Cue product;

    @Column(name = "quantity")
    private Long quantity;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

//    public Order getOrder() {
//        return order;
//    }

//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public OrderLine() {
    }

    public OrderLine(Long id, Cue product, Long quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
    public Long priceLine(){
        return this.product.getPrice()*this.quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Cue getProduct() {
        return product;
    }

    public void setProduct(Cue product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}