package com.cueshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}