package com.demo.namartejshop.controller;

import com.demo.namartejshop.model.Productos;
import com.demo.namartejshop.model.Review;
import com.demo.namartejshop.model.Tiendas;
import com.demo.namartejshop.model.enums.ClothesType;
import com.demo.namartejshop.model.enums.ProductType;
import com.demo.namartejshop.repository.ProductosRepository;
import com.demo.namartejshop.repository.ReviewRepository;
import com.demo.namartejshop.repository.TiendasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductosRepository productosRepository;
    private ReviewRepository reviewRepository;
    private final TiendasRepository tiendasRepository;

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

    // Get newProduct
    @GetMapping("products/new")
    public String newProduct(Model model){
        // añadir objeto product vacio para rellenarlo desde le formulario
        model.addAttribute("product", new Productos());
        // datos para el  productTypes
        model.addAttribute("productType", ProductType.values());
        model.addAttribute("tiendas", tiendasRepository.findAll());
        return "Products/product-form";
    }
    // Get editDish

    // Post saveDish
    @PostMapping("products")
    public String saveProduct(@ModelAttribute Productos productos){
        productosRepository.save(productos);
        return "redirect:/products";
    }
}
