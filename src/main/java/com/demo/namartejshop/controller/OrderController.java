package com.demo.namartejshop.controller;


import com.demo.namartejshop.model.Order;
import com.demo.namartejshop.model.Tiendas;
import com.demo.namartejshop.model.enums.OrderStatus;
import com.demo.namartejshop.repository.OrderLineRepository;
import com.demo.namartejshop.repository.OrderRepository;
import com.demo.namartejshop.repository.TiendasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final TiendasRepository tiendasRepository;

    @GetMapping("orders")
    public String orders(Model model){
        model.addAttribute("orders", orderRepository.findAll());
        return "orders/order-list";
    }

    //GetMapping order/
    @GetMapping("orders/{id}")
    public String order(Model model, @PathVariable Long id){
        model.addAttribute("order", orderRepository.findById(id).orElseThrow());
        model.addAttribute("orderLines", orderLineRepository.findByOrder_Id(id));
        return "orders/order-detail";
    }



    @GetMapping("orders/new")
    public String newOrder(Model model, @RequestParam Long tiendasId){
        Tiendas tienda = tiendasRepository.findById(tiendasId).orElseThrow();
        Order order = new Order();
        order.setTiendas(tienda);
        model.addAttribute("tienda", tienda);
        return "orders/order-form";
    }


    @PostMapping("orders")
    public String save(@ModelAttribute Order order){
        order.setStatus(OrderStatus.ORDEN_RECIBIDA);
        order.setFecha(LocalDateTime.now());
        order.setTotalPrice(0d);
        orderRepository.save(order);
        return "redirect:/orders/" + order.getId();
    }


}
