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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String register(@ModelAttribute RegisterForm form, RedirectAttributes redirectAttributes){
     try {
         userService.register(form);
         redirectAttributes.addFlashAttribute("message", "Cuenta creada correctamente. Inicie sesión.");
         return "redirect:/login";
     } catch (Exception e){
         redirectAttributes.addFlashAttribute("error", e.getMessage());
         return "redirect:/register";
     }
    }

    // @GetMapping para el /login
    @GetMapping("login")
    public String login(){

        return "auth/login";
    }




    // No hace falta @PostMapping para el /login ni para el /logout
    // porque Spring security lo hace automáticamente
}
