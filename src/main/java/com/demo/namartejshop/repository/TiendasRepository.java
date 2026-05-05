package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Tiendas;
import com.demo.namartejshop.model.enums.ClothesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TiendasRepository extends JpaRepository<Tiendas, Long> {
    //    @Transactional
    //    @Modifying
    //    @Query("delete from Restaurant r where r.name = ?1")
    //    void deleteByNameTodoGuay(String name);

    List<Tiendas> findByActiveTrue();
    Optional<Tiendas> findByIdAndActiveTrue(Long id);

    // Query con filtros:
    @Query("""
        SELECT t from Tiendas t
        WHERE t.active = true
        AND (:clothesType IS NULL OR t.clothesType = :clothesType)
        AND (:price IS NULL OR t.averageprice <= :price) 
        AND (:title IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :title, '%')))
                """)
    List<Tiendas> findActiveFiltering(
            @Param("clothesType") ClothesType clothesType,
            @Param("price") Double price,
            @Param("title") String title
            );

    // Filtro por nombre

    // Filtro por foodType

    // Filtro por precio

    // Filtro por código postal

    // Si se vuelven muy complejos los filtros se puede usar:
    // API Specification
}