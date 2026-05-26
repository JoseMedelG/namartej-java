package com.demo.namartejshop.dto;

import com.demo.namartejshop.model.Order;
import com.demo.namartejshop.model.Review;

import java.util.List;

// Informacion del usuario que no esta en la tabla usuario
// porque esta en sus asociaciones
// A futuro se puede añadir mas info relacionada con el usuario:
// ejemplo: favoritos, puntos para fidelización
public record UserStatsDTO(long countReviews,
                           List<Review> reviews,
                           long countOrders,
                           List<Order> orders,
                           double moneySpent) {


}
