package com.demo.namartejshop.model;

import com.demo.namartejshop.model.enums.OrderStatus;
import jakarta.persistence.*;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pedidos")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private LocalDateTime fecha = LocalDateTime.now();
    private String customerEmail;
    private String customerAddress;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ORDEN_RECIBIDA; //Cambie el nombre de RECIBIDO a ORDEN_RECIBIDA por entendimiento

    @ManyToOne
    private Tiendas tiendas;
//@ManyToOne
//    private Employee empleado;

    // Constructor vacío
        public Order() {}

    // Constructor con parámetros
    public Order(String customerName, String customerEmail, String customerAddress, Double totalPrice, Tiendas tiendas) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.totalPrice = totalPrice;
        this.tiendas = tiendas;
    }

    // Getters and Setters (NO puse el id, ni el totalprice porque se generan automaticamente)
    public Tiendas getTiendas() {
        return tiendas;
    }

    public void setTiendas(Tiendas tiendas) {
        this.tiendas = tiendas;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // toString (No puse la tienda porque
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", fecha=" + fecha +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
