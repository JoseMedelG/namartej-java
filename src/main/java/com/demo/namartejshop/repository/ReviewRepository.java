package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByTiendas_IdOrderByCreationDateDesc(Long tiendasId);
    List<Review> findByProductos_IdOrderByCreationDateDesc(Long productosId);


    List<Review> findByTiendas_IdAndRatingGreaterThanEqualOrderByCreationDateDesc(Long id, Integer rating);

}