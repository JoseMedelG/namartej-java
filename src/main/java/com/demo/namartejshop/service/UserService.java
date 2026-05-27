package com.demo.namartejshop.service;


import com.demo.namartejshop.dto.RegisterForm;
import com.demo.namartejshop.dto.UserStatsDTO;
import com.demo.namartejshop.model.User;
import com.demo.namartejshop.model.enums.Role;
import com.demo.namartejshop.repository.OrderRepository;
import com.demo.namartejshop.repository.ReviewRepository;
import com.demo.namartejshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;


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
        return userRepository.save(user); // Guarda el usuario
    }

    // TODO metodo para crear usuario admin
    public List<User> findAll() {
        return userRepository.findAll();
//                .stream().map(user -> )
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    public UserStatsDTO findStatsById(Long id){
        return new UserStatsDTO(
                reviewRepository.countByUser_Id(id),
                reviewRepository.findByUser_Id(id),
                orderRepository.countByUser_Id(id),
                orderRepository.findByUser_IdOrderByFechaDesc(id),
                orderRepository.calculateTotalMoneySpentByUserId(id)
        );
    }

    public User create(User user){
        if(userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("Nombre de usuario ya existente");

        if(userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("El email ya existe");

//        if(user.getPassword() == null || user.getPassword().isBlank())
//            throw new RuntimeException("La contraseña no puede estar vacía");

        if(!StringUtils.hasText(user.getPassword()))
            throw new RuntimeException("La contraseña no puede estar vacía");

        user.setPassword(passwordEncoder.encode(user.getPassword())); // password cifrada con bcrypt
        return userRepository.save(user);

    }

    public User update(User userForm){
        User userDB = findById(userForm.getId()); // traigo el usuario de la Base de Datos

        userDB.setUsername(userForm.getUsername());
        userDB.setEmail(userForm.getEmail());
        userDB.setRole(userForm.getRole());

        if(StringUtils.hasText(userForm.getPassword()))
            userDB.setPassword(passwordEncoder.encode(userForm.getPassword()));

        return userRepository.save(userDB);
    }
}