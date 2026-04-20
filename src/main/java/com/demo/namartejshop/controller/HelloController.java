package com.demo.namartejshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // HTTP- GET
    @GetMapping("/hola") // Esta es la URL
    public String hola() {
        return "Hello"; //nombre del archivo HTML
    }

    @GetMapping("adios")
    public String adios(Model model){
        model.addAttribute("mensaje", "Adios mundo cruel");
        return "Bye";
    }

}
