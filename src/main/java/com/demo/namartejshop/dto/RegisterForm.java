package com.demo.namartejshop.dto;


import lombok.Data;

@Data
public class RegisterForm {

    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
}
