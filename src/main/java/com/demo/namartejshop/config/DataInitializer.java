package com.demo.namartejshop.config;

import com.demo.namartejshop.dto.RegisterForm;
import com.demo.namartejshop.model.User;
import com.demo.namartejshop.model.enums.Role;
import com.demo.namartejshop.repository.UserRepository;
import com.demo.namartejshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Opción usando el service:
       User user = userService.register(RegisterForm.builder()
                       .username("user")
                       .email("user@gmail.com")
                       .password("user")
                       .passwordConfirm("user")
               .build());

        // Opción usando directamente le repository
       User admin = userRepository.save(User.builder()
                       .username("admin")
                       .email("admin@gmail.com")
                       .password(passwordEncoder.encode("admin"))
                       .role(Role.ROLE_ADMIN)
               .build());
    }
}
