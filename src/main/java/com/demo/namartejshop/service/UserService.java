package com.demo.namartejshop.service;


import com.demo.namartejshop.model.User;
import com.demo.namartejshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


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
}