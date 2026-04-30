package com.demo.namartejshop.controller;


import com.demo.namartejshop.repository.OrderLineRepository;
import com.demo.namartejshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

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


}
