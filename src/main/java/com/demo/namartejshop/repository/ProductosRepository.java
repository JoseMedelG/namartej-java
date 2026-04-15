package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Employee;
import com.demo.namartejshop.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository  extends JpaRepository<Productos, Long> {




}
