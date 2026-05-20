package com.demo.namartejshop.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {

    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
}
