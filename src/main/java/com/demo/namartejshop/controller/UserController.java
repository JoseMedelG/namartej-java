package com.demo.namartejshop.controller;

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
        model.addAttribute("user", userService.findById(id));
        return "users/user-detail";

    }



    // user Form
}
