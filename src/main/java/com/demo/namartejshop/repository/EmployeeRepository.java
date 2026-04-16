package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.enums.ClothesType;
import com.demo.namartejshop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query("select e from Employee e order by e.firstName")
    List<Employee> findByOrderByFirstNameAsc();    //La query no esta filtrando solo esta ordenando por nombre
    // OTRAS FORMAS DE ORDENAR TIPICAS SERIA ORDENAR POR PRECIO ASC EN PRODUCTOS
}