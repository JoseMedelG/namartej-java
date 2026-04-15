package com.demo.namartejshop.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nameProduct;

    @Column(length = 500)
    private String description;

    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "clothes_type")
    private ClothesType clothesType;

    @ManyToOne
    private Tiendas tienda;

    public Productos() {}
    public Productos(Long id, String nameProduct, String description, Double price, ClothesType clothesType, Tiendas tienda) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.clothesType = clothesType;
        this.tienda = tienda;
    }

    // Los getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ClothesType getClothesType() {
        return clothesType;
    }

    public void setClothesType(ClothesType clothesType) {
        this.clothesType = clothesType;
    }



    public Tiendas getTienda() {
        return tienda;
    }

    public void setTienda(Tiendas tienda) {
        this.tienda = tienda;
    }

    // toString
    @Override
    public String toString() {
        return "Productos{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", clothesType=" + clothesType +
                ", tienda=" + tienda +
                '}';
    }
}
