package com.demo.namartejshop.controller;


import com.demo.namartejshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    // @GetMapping para el /register

    // @GetMapping para el /login



    // @PostMpaping /register


    // No hace falta @PostMapping para el /login ni para el /logout
    // porque Spring security lo hace automáticamente
}
