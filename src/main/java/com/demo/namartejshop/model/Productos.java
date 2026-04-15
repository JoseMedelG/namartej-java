package com.demo.namartejshop.model;

import jakarta.persistence.*;



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

    // private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    @ManyToOne
    private Tiendas tienda;

    public Productos() {}
    public Productos(Long id, String nameProduct, String description, Double price, ProductType productType, Tiendas tienda) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.productType = productType;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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
                ", productType=" + productType +
                ", tienda=" + tienda +
                '}';
    }
}
