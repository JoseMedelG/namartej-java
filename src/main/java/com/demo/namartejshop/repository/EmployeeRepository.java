package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.ClothesType;
import com.demo.namartejshop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    // Metodos de consulta derivados (derived queries) basado ne el nombre del metodo
    List<Employee> findByDni(String dni);

    List<Employee> findByAge(Integer age);

    List<Employee> findByTienda_Name(String tiendaName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByAgeGreaterThanEqual(Integer age);

    List<Employee> findByTienda_ClothesType(ClothesType clothesType);
}