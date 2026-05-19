package com.demo.namartejshop.controller;


import com.demo.namartejshop.dto.RegisterForm;
import com.demo.namartejshop.model.User;
import com.demo.namartejshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    // @GetMapping para el /register
    @GetMapping("register")
    public String register(Model model){
        // opción 1: pasar a una entidad User
       // model.addAttribute("user", new User());
        // Opción 2: dto RegisterForm
        model.addAttribute("user", new RegisterForm()); // puede llamarse asi o UserRegisterDTO
        return "auth/register";
    }


    // @PostMpaping /register
    @PostMapping("register")
    public String register(@ModelAttribute RegisterForm form){
        System.out.println(form);
        // verificar si username ocupado
        // Verificar email ocupado
        // verificar password

        return "redirect:/login";
    }

    // @GetMapping para el /login
    @GetMapping("login")
    public String login(){

        return "auth/login";
    }




    // No hace falta @PostMapping para el /login ni para el /logout
    // porque Spring security lo hace automáticamente
}
