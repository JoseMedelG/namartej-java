package com.demo.namartejshop.controller;


import com.demo.namartejshop.model.*;
import com.demo.namartejshop.model.enums.OrderStatus;
import com.demo.namartejshop.model.enums.Role;
import com.demo.namartejshop.repository.OrderLineRepository;
import com.demo.namartejshop.repository.OrderRepository;
import com.demo.namartejshop.repository.ProductosRepository;
import com.demo.namartejshop.repository.TiendasRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final TiendasRepository tiendasRepository;
    private final ProductosRepository productosRepository;

    @GetMapping("orders")
    public String orders(Model model, @AuthenticationPrincipal User user) {
        if(user.getRole() == Role.ROLE_ADMIN){
            model.addAttribute("orders", orderRepository.findAll());
        } else {
            model.addAttribute("orders", orderRepository.findByUser_IdOrderByFechaDesc(user.getId()));
        }

        return "orders/order-list";
    }

    //GetMapping order/
    @GetMapping("orders/{id}")
    public String order(Model model, @PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        model.addAttribute("order", order);
        model.addAttribute("orderLines", orderLineRepository.findByOrder_Id(id));
        model.addAttribute("countUserOrders", orderRepository.countByUser_Id(order.getUser().getId()));
        model.addAttribute("totalMoneyUserSpent", orderRepository.calculateTotalMoneySpentByUserId(order.getUser().getId()));
        //  cargar productos filtrando por tienda
        List<Productos> productos = productosRepository.findByTienda_IdOrderByPrice(order.getTiendas().getId());
        model.addAttribute("productos", productos);
        return "orders/order-detail";
    }


    @GetMapping("orders/new")
    public String newOrder(Model model, @RequestParam Long tiendaId) {
        Tiendas tiendas = tiendasRepository.findById(tiendaId).orElseThrow();
        Order order = new Order();
        order.setTiendas(tiendas);
        model.addAttribute("order", order);
        return "orders/order-form";
    }


//    @PostMapping("orders")
//    public String save(@ModelAttribute Order order) {
//        order.setStatus(OrderStatus.ORDEN_RECIBIDA);
//        order.setFecha(LocalDateTime.now());
//        order.setTotalPrice(0d);
//        orderRepository.save(order);
//        return "redirect:/orders/" + order.getId();
//    }

    @PostMapping("orders")
    public String save(@ModelAttribute Order order, @RequestParam Long tiendaId, @AuthenticationPrincipal User user) {
        Tiendas tienda = tiendasRepository.findById(tiendaId).orElseThrow();
        order.setTiendas(tienda);
        order.setStatus(OrderStatus.ORDEN_RECIBIDA);
        order.setFecha(LocalDateTime.now());
        order.setUser(user);
        order.setTotalPrice(0d);
        orderRepository.save(order);
        return "redirect:/orders/" + order.getId();
    }


    @PostMapping("orders/{id}/lines")
    public String addLineProductos(
            @PathVariable Long id, @RequestParam Long productoId) {

        Order order = orderRepository.findById(id).orElseThrow();
        Productos productos = productosRepository.findById(productoId).orElseThrow();

        Optional<OrderLine> lineOptional = orderLineRepository.findByOrder_IdAndProductos_Id(id, productoId);

        // opción imperativa clásica tradicional
        OrderLine orderLine;
        if (lineOptional.isPresent()) {
            orderLine = lineOptional.get();
            orderLine.setQuantity(orderLine.getQuantity() + 1);
        } else {
            orderLine = new OrderLine();
            orderLine.setProductos(productos);
            orderLine.setOrder(order);
            orderLine.setQuantity(1);
        }
        orderLineRepository.save(orderLine);
//        // opción alternativa estilo funcional
//        OrderLine line = orderLineRepository
//                .findByOrder_IdAndProductos_Id(id, productoId)
//                .orElseGet(() -> new OrderLine(0, order, productos));
////
////        line.setQuantity(line.getQuantity() + 1);
////        orderLineRepository.save(line);


        if (order.getStatus() == OrderStatus.ORDEN_RECIBIDA) {
            order.setStatus(OrderStatus.EN_PROCESO);
        }

        Double totalPrice = orderLineRepository.calculateTotalPrice(order.getId());
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        return "redirect:/orders/" + order.getId();
    }

    // Si en la finalización se van a enviar datos sensibles mejor que sea PostMapping
    @GetMapping("orders/{id}/entregado")
    public String finish(@PathVariable Long id, @RequestParam(required = false) Double tip) {
        Order order =  orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.ENTREGADO);
        order.setTotalPrice(orderLineRepository.calculateTotalPrice(order.getId()));
        // tip, iva, service charge, terrace
        if(tip != null && tip > 0){
            order.setTotalPrice(order.getTotalPrice() + tip);
        } else{
            order.setTotalPrice(order.getTotalPrice());
        }

        orderRepository.save(order);
        return "redirect:/orders/" + id;
    }

    @GetMapping("orders/{orderId}/lines/{lineId}/delete")
    public String deleteLine(@PathVariable Long orderId, @PathVariable Long lineId) {
        orderLineRepository.deleteById(lineId);
        Order order =  orderRepository.findById(orderId).orElseThrow();
        order.setTotalPrice(orderLineRepository.calculateTotalPrice(order.getId()));
        orderRepository.save(order);
        return "redirect:/orders/" + orderId;
    }

    @PostMapping("orders/{orderId}/lines/{lineId}")
    public String updateLineQuantity(
            @PathVariable Long orderId,
            @PathVariable Long lineId,
            @RequestParam Integer quantity) {
        if(quantity >= 1){
            OrderLine orderLine = orderLineRepository.findById(lineId).orElseThrow();
            orderLine.setQuantity(quantity);
            orderLineRepository.save(orderLine);
            Order order =  orderRepository.findById(orderId).orElseThrow();
            order.setTotalPrice(orderLineRepository.calculateTotalPrice(order.getId()));
            orderRepository.save(order);
        }
        return "redirect:/orders/" + orderId;
    }


}