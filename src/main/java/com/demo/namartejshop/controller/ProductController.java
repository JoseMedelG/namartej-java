package com.demo.namartejshop.controller;

import com.demo.namartejshop.model.Productos;
import com.demo.namartejshop.repository.ProductosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductosRepository productosRepository;

    public ProductController(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @GetMapping("products/{id}")
    public String tiendaDetail(@PathVariable Long id, Model model){

        Optional<Productos> productoOptional = productosRepository.findById(id);
        if (productoOptional.isPresent()) {
            Productos productos = productoOptional.get();
            model.addAttribute("producto", productos);
            return "Products/producto-detail";

        }
        return "redirect:/tiendas";
    }
}
