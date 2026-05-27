package com.demo.namartejshop.controller;

import com.demo.namartejshop.model.User;
import com.demo.namartejshop.model.enums.Role;
import com.demo.namartejshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class UserController {

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
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", false);
        return "users/user-form";
    }

    //@GetMapping("admin/users/edit/{id}")



    // user Form
}
