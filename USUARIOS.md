## Autentiacion y autorización con usuarios co Spirng security

Ahora mismo la aplicaci todo el mundo ve todo.

No hay usuarios.

Queremos regsutro y login de usuarios, para que un usuario pueda iniaciar sesion y ver su perfil, 
hacer pedidos, ver pedidos, hacer reviews, etc.

Queremos que haya usuario admin con rol ROLE_ADMIN que pueda gestionar los productos, pedidos, etc.

Para esto vamos a usar Spring Security, que es un framework de seguridad para aplicaciones web en Java.

## Paso 1: Añadir dependencias en pomp.xml {OK}

* Spring-boot-starter-security
* thymeleaf-extras-springsecurity6
  * spring-security-test (para pruebas)
* Opcional: spring-bpoot-starter-validation (para validaciones)

## Paso 2: Crear entidad User y enum Role {OK}

* enums/Role.java: ROLE_USER, ROLE_ADMIN
* model/USER.java: id, username, password, email, roles (Set<Role>)
  * implements UserDetails de Spring Security

## Paso 3: Crear repositorio UserRepository {OK}

* repository/UserRepository.java: extends JpaRepository<User, Long>
  * findByUsername(String username)
  * boolean existsByUsername(String username)
  * boolean existsByEmail(String email)

## Paso 4: Crear servicio UserService {OK}

* service/UserService.java
  * implements UserDetailsService de Spring Security
  * loadUserByUsername(String username) sirve para que Spring Security
  pueda cargar al usuario a partir del username y comparar la contraseña al
  hacer login y demás

## Paso 5: Crear SecurityConfig {OK}

* config/Securityconfig.java
  * Creams dos @Bean
  * securityFilterChain configura rutas protegidas, login, logout, etc.
  * passwordEncoder() para cifrar contraseñas
  
## Paso 6: Controlador AuthController para registro y login {Ok}

* Controller/AuthController.java
  * GET /login - muestra el formulario de login
  * GET /register - muestra el formulario de registro
  * POST /register - procesa el registro, crea el usuario con rol ROLE_USER

## Paso 7: Crear vistas Thymeleaf para login y registro 

+ templates/auth/login.html (OK)
+ templates/auth/register.html (ok)
+ templates/fragment/navbar.html - mostrar enlaces de login/register o
longout dependiendo de si el usuario está autenticado o no, usando thymeleaf-extras-springsecurity6


## Paso 8: Opcional: ViewSecurityAdvice.java

Objetivo: 'th:if="${isAuthenticated()}"' o 'th:if="${isAdmin()}"' 

* config/ViewSecurityAdvice.java
  * isAuthenticated() para usar en thymeleaf y mostrar/ocultar partes de la vista 
  dependiendo de si el usuario esta autenticado o no
  * isAdmin()
  * currentUserName()

## Paso 9: integración de User con Order y Review

* Order.java: añadir campo User user con @ManyToOne
* Review.java: añadir campo User user con @ManyToOne

En controller OrderController, al crear un pedido, asignar el usuario autenticado al pedido.
En controller ReviewController, al crear una review, asignar el usuario autenticado a la review.

## Paso 10: Crear usuario demo 
 * para probar la aplicación sin tener que registrarse cada vez, podemos crear un usuario
demo en una clase de configuración o usando CommandLineRunner