package com.demo.namartejshop.service;


import com.demo.namartejshop.dto.RegisterForm;
import com.demo.namartejshop.model.User;
import com.demo.namartejshop.model.enums.Role;
import com.demo.namartejshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //metodo parta buscar el usuario en base de dtos por su username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //  Opcion tradicional
//        Optional <User> user = userRepository.findByUsername(username);
//        if(user.isPresent()){
//            return user.get();
//    } else{
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

        // Opción funcional
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        // cargar el usuario de base de datos por username
    }

    public User register(RegisterForm form) {

        if(userRepository.existsByUsername(form.getUsername()))
            throw new RuntimeException("Nombre de usuario ya existente");

        if(userRepository.existsByEmail(form.getEmail()))
            throw new RuntimeException("El email ya existe");

        if(! form.getPassword().equals(form.getPasswordConfirm()))
            throw new RuntimeException("Las contraseñas no coinciden");


        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword())); // password cifrada con bcrypt
        user.setRole(Role.ROLE_USER); // por defecto todos los usuarios registrados son ROLE_USER
        return userRepository.save(user);
    }
}