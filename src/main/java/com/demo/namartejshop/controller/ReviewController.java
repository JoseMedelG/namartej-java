package com.demo.namartejshop.controller;

import com.demo.namartejshop.model.Review;
import com.demo.namartejshop.repository.ProductosRepository;
import com.demo.namartejshop.repository.ReviewRepository;
import com.demo.namartejshop.repository.TiendasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class ReviewController {

    // Inyectar el repositorios necesarios
    private final ReviewRepository reviewRepository;
    private final TiendasRepository tiendasRepository;
    private final ProductosRepository productosRepository;

    //getmapping reviews
    @GetMapping("reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewRepository.findAll());
        return "reviews/review-list";
    }

    @GetMapping("reviews/{id}")
    public String review(Model model, @PathVariable Long id){
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        return "reviews/review-detail";
    }

    @GetMapping("reviews/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        reviewRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "¡Borrado exitoso!");
        return "redirect:/reviews";
    }

    @GetMapping("reviews/new")
    public String newReview(
            Model model,
            @RequestParam(required = false) Long tiendaId,
            @RequestParam(required = false) Long productoId){
        Review review = new Review();

        if(tiendaId != null)
            review.setTiendas(tiendasRepository.findById(tiendaId).orElseThrow());

        if(productoId != null)
            review.setProductos(productosRepository.findById(productoId).orElseThrow());

        model.addAttribute("review", review);
        return "reviews/review-form";
    }


}
