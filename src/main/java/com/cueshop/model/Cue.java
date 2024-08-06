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
@Table(name = "cue")
public class Cue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id", nullable = false)
    private Long id;

    //@Column(name = "name")
    private String name;

    //@Column(name = "price")
    private Long price;

    //@Column(name = "brand")
    private String brand;

    //@Column(name = "image")
    private String image;

    //@Column(name = "spec")
    private String specifications;
}