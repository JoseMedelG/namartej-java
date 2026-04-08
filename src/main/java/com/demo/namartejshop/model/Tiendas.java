package com.demo.namartejshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tiendas_Madrid")
public class Tiendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    private String name;

    private Double averageprice;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active;

    private Integer numberEmployees;



}
