package com.demo.namartejshop.controller;


import com.demo.namartejshop.model.Productos;
import com.demo.namartejshop.model.Review;
import com.demo.namartejshop.model.Tiendas;
import com.demo.namartejshop.model.enums.ClothesType;
import com.demo.namartejshop.repository.ProductosRepository;
import com.demo.namartejshop.repository.ReviewRepository;
import com.demo.namartejshop.repository.TiendasRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TiendasController {

    // Inyectar repositorios de tiendas y productos
    private final TiendasRepository tiendasRepository;
    private final ProductosRepository productosRepository;
    private final ReviewRepository reviewRepository;

    public TiendasController(TiendasRepository tiendasRepository, ProductosRepository productosRepository, ReviewRepository reviewRepository) {
        this.tiendasRepository = tiendasRepository;
        this.productosRepository = productosRepository;
        this.reviewRepository = reviewRepository;
    }

//    @GetMapping("tiendas")
//    public String tiendas(Model model){
//        model.addAttribute("tiendas", tiendasRepository.findAll());
//        return "Tiendas/tiendas-list";
//
//    }

    /*
    Resumen de métodos típicos en una clase controller:
    @GetMapping("restaurants") findAll [OK]
    @GetMapping("restaurants/{id}") findById []
    @GetMapping("restaurants/delete/{id}") delete []



    @GetMapping("restaurants/create") createForm
    @PostMapping("restaurants/create") create

    @GetMapping("restaurants/{id}/edit") editForm
    @PostMapping("restaurants/{id}/edit") edit
     */

        // get all restaurants
        // http://localhost:8080/tiendas

    //    // http://localhost:8080/restaurants
//    @GetMapping("restaurants") // controlador
//    public String restaurantList(Model model) {
//        // cargar datos en el modelo
    ////        List<Tiendas> tiendas = tiendasRepository.findAll();
//        List<Tiendas> Tiendas = tiendasRepository.findByActiveTrue();
//        model.addAttribute("tiendas", tiendas);
//        model.addAttribute("numTiendas", tiendas.size());
//        model.addAttribute("title", "Lista de tiendas");
//        return "tiendas/tiendas-list"; // vista
//    }
    // http://localhost:8080/tiendas?clothesType=STREETWEAR
    // http://localhost:8080/tiendas?price=15
    // http://localhost:8080/tiendas?title=camiseta
        @GetMapping("tiendas") // controlador
//        public String tiendasList(Model model) {
//            // cargar datos en el modelo
//            // List<Restaurant> restaurants = restaurantRepository.findAll();
//            List<Tiendas> tiendas = tiendasRepository.findByActiveTrue();
         public String tiendasList(
                Model model,
                @RequestParam(required = false) ClothesType clothesType,
                @RequestParam(required = false) Double price,
                @RequestParam(required = false) String title

        ) {
            List<Tiendas> tiendas = tiendasRepository.findActiveFiltering(clothesType, price, title);
            model.addAttribute("tiendas", tiendas);
            model.addAttribute("numTiendas", tiendas.size());
            model.addAttribute("title", "Lista de tiendas");
            return "Tiendas/tiendas-list"; // vista
        }

        // nuevo metodo para traer un solo restaurante por su id
        @GetMapping("tiendas/{id}")
        public String restaurantDetail(@PathVariable Long id, Model model) {

            // buscar tienda por su id: findById
            // Optional<Tiendas> tiendasOptional = tiendasRepository.findById(id);
            Optional<Tiendas> tiendasOptional = tiendasRepository.findByIdAndActiveTrue(id);
            if (tiendasOptional.isPresent()) {

                // La tienda sí existe
                Tiendas tienda = tiendasOptional.get();
                model.addAttribute("tienda", tienda);
                // opcional:
                // cargar los productos (Productos) de esta tienda en el model
                List<Productos> product = productosRepository.
                        findByTienda_IdOrderByPrice(
                                tienda.getId());
                model.addAttribute("product", product);

                //reviews
                List<Review> reviews = reviewRepository.findByTiendas_IdOrderByCreationDateDesc(tienda.getId());
                model.addAttribute("reviews", reviews);
                return "Tiendas/tienda-detail";

            }

            // la tienda NO existe
            // CUIDADO no apunta a HTML
            // APUNTA al Controller
            return "redirect:/tiendas";
        }
            @GetMapping("tiendas/deactivate/{id}")
            public String restaurantDeactivate(@PathVariable Long id, Model model) {
                Optional<Tiendas> tiendaOptional = tiendasRepository.findById(id);

                if(tiendaOptional.isPresent()) {
                    Tiendas tiendas = tiendaOptional.get();
                    tiendas.setActive(false);
                    tiendasRepository.save(tiendas);
                }

                return "redirect:/tiendas";
            }


            //ruta para entrar al formulario de restaurante
            @GetMapping("tiendas/new")
            public String newTiendas(Model model){
            // añadir objeto Tiendas vacio para rellenarlo desde le formulario
                model.addAttribute("tienda", new Tiendas());
                // datos para el clothes types
                model.addAttribute("clothesTypes", ClothesType.values());
                // categorias
                return "Tiendas/tienda-form";
            }

            @PostMapping("tiendas")
            public String createTienda(@ModelAttribute Tiendas tiendas){

                System.out.println("TIENDA RECIBIDA: " + tiendas);
                tiendasRepository.save(tiendas);
                return "redirect:/tiendas/" + tiendas.getId();
            }


    }

