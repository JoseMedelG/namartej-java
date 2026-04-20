package com.demo.namartejshop.controller;


import com.demo.namartejshop.repository.TiendasRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendasController {

    //Inyectar el tiendas repository
    private final TiendasRepository tiendasRepository;

    public TiendasController(TiendasRepository tiendasRepository) {
        this.tiendasRepository = tiendasRepository;
    }

    @GetMapping("tiendas")
    public String tiendas(Model model){
        model.addAttribute("tiendas", tiendasRepository.findAll());
        return "Tiendas/tiendas-list";
    }
}
