package com.demo.namartejshop.model;

import com.demo.namartejshop.model.enums.ClothesType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tiendas_España")
public class Tiendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    private String name;

    private Double averageprice;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    private Integer numberEmployees;

    // fecha fundación
    //@CreationTimestamp // esta anotacion es para que la base de datos genere automaticamente la fecha sin
    // precuparte por tener que cambiarla
    private LocalDate starDate = LocalDate.now(); // Valor por defecto de la fecha actual

    // tipo de ropa
    @Enumerated(EnumType.STRING)
    private ClothesType clothesType = ClothesType.Streetwear;


    // metodo constructor para crear Tiendas con valores
    public Tiendas(String name, Double averageprice, Integer numberEmployees) {
        this.name = name;
        this.averageprice = averageprice;
        this.numberEmployees = numberEmployees;
    }
    // metodo constructor vacio
    public Tiendas(){

    }
    // Metodos Getter y Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageprice() {
        return averageprice;
    }

    public void setAverageprice(Double averageprice) {
        this.averageprice = averageprice;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public LocalDate getStarDate() {
        return starDate;
    }
    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }
    public ClothesType getClothesType() {
        return clothesType;
    }

    public void setClothesType(ClothesType clothesType) {
        this.clothesType = clothesType;
    }

    public void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
    }
 // creamos un toString para que aparezacn datos en la terminal

    @Override
    public String toString() {
        return "Tiendas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageprice=" + averageprice +
                ", active=" + active +
                ", numberEmployees=" + numberEmployees +
                ", starDate=" + starDate +
                ", clothesType=" + clothesType +
                '}';
    }
}
