package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Tiendas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TiendasRepository extends JpaRepository<Tiendas, Long> {
    //    @Transactional
    //    @Modifying
    //    @Query("delete from Restaurant r where r.name = ?1")
    //    void deleteByNameTodoGuay(String name);

    List<Tiendas> findByActiveTrue();
    Optional<Tiendas> findByIdAndActiveTrue(Long id);

}