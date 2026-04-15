package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Employee;
import com.demo.namartejshop.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductosRepository  extends JpaRepository<Productos, Long> {
    List<Productos> findByPriceLessThanEqual(Double price);


    // Consulta para tarer todos los productos de una tienda concreta por id
    // ordenados por precio ascendente
    List<Productos> findByTienda_IdOrderByPrice(Long tiendaId); //Esta es ascendente por defecto



}
