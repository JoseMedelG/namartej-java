package com.demo.namartejshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // securityFilterChain para proteger acceso a rutas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // h2 usa iframes

        http.authorizeHttpRequests(
                auth -> auth
                        // Rutas publicas tanto GET como POST
                .requestMatchers("/hola", "/adios", "/login", "/register",
                                "/css/**", "/images/**", "/webjars/**").permitAll()

                // listado y detalles publicos solo por GET, no POST
                 .requestMatchers(HttpMethod.GET, "/tiendas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tiendas/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tiendas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tiendas/desactivate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tiendas/activate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tiendas/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tiendas/edit/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/productos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/productos/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/productos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/productos/desactivate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/productos/activate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/productos/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/productos/edit/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/reviews").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reviews/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reviews").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/reviews/delete/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reviews/new").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/reviews/edit/*").hasRole("USER")

                           //solo user normal, no admin
//                        .requestMatchers(HttpMethod.GET, "/orders").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/orders/new").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/orders/**").hasRole("USER")

                        // todos los roles
                        .requestMatchers(HttpMethod.GET, "/orders", "/orders/**", "/orders/new").authenticated()

                        // lo demas autenticar, no importa el rol
                        .anyRequest().authenticated()
        );

        http.formLogin(form ->
                form.loginPage("/login")
                        .defaultSuccessUrl("/tiendas", true)
                        .permitAll()
        );

        // TODO h2

        // TODO logOut

        return http.build();

    }

}
