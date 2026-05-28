package com.demo.namartejshop.controller;

import com.demo.namartejshop.model.User;
import com.demo.namartejshop.model.enums.Role;
import com.demo.namartejshop.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j
@AllArgsConstructor
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    // user List
    @GetMapping("admin/users")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/user-list";
    }

    // User Detail
    @GetMapping("admin/users/{id}")
    public String detail(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id)); // user
        model.addAttribute("userStats", userService.findStatsById(id)); // UserStatsDTO
        return "users/user-detail";

    }

    @GetMapping("admin/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", false);
        return "users/user-form";
    }

    @GetMapping("admin/users/edit/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        user.setPassword(null); // No devolver esta password cifrada
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", true);
        return "users/user-form";
    }

    @PostMapping("admin/users")
    public String save(
            @ModelAttribute User user,
            RedirectAttributes rediA,
            @RequestParam("imageFile") MultipartFile imageFile){
        log.info("Guardando user: {}", user.getUsername());
        log.info("imagen recibida {}", imageFile);

        // Creación
        try {
            if (user.getId() == null) {
                userService.create(user);
                rediA.addFlashAttribute("message", "Usuario creado correctamente");
            } else {
                // Edicion
                userService.update(user);
                rediA.addFlashAttribute("message", "Usuario actualizado correctamente");
            }

        } catch (Exception e) {
            log.warn("error al guardar user {}", user, e);
            rediA.addFlashAttribute("error", e.getMessage());
            return user.getId() == null ?
                    "redirect:/admin/users/new" : "redirect:/admin/users/edit/" + user.getId();
        }

        return "redirect:/admin/users";


        // user Form
    }
}