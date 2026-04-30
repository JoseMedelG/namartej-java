package com.demo.namartejshop.controller;

import com.demo.namartejshop.model.Productos;
import com.demo.namartejshop.model.Review;
import com.demo.namartejshop.repository.ProductosRepository;
import com.demo.namartejshop.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductosRepository productosRepository;
    private ReviewRepository reviewRepository;

    @GetMapping("products")
    public String listProducts(Model model){
        List<Productos> productos  = productosRepository.findAll();
        model.addAttribute("productos", productos);
        return "Products/producto-list";

    }


    @GetMapping("products/{id}")
    public String tiendaDetail(@PathVariable Long id, Model model){

        Optional<Productos> productoOptional = productosRepository.findById(id);
        if (productoOptional.isPresent()) {
            Productos productos = productoOptional.get();
            model.addAttribute("producto", productos);
            // traer reviews de este producto y cargar en model
            List<Review> reviews= reviewRepository.findByProductos_IdOrderByCreationDateDesc(id);
            model.addAttribute("reviews", reviews);
            return "Products/producto-detail";

        }
        return "redirect:/tiendas";
    }
}
