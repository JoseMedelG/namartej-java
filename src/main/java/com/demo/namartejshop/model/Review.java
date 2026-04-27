package com.demo.namartejshop.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

// Anotaciones JPA
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private Integer rating;

    @Builder.Default // para que el builder no ponga este campo a null
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private Tiendas tiendas;



}
