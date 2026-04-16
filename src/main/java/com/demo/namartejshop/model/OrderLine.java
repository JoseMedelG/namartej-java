package com.demo.namartejshop.model;


import jakarta.persistence.*;

@Entity
@Table (name = "Lineas_Pedido")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Productos productos;

    // Contrcustor vacio
    public OrderLine(){
    }

    // Constructor con parametros
    public OrderLine(Integer quantity, Order order, Productos productos) {
        this.quantity = quantity;
        this.order = order;
        this.productos = productos;
    }

    //Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    // toString
    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
