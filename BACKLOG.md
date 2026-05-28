
Tareas:

In progress:
* Usuarios con Spirng security




Pendiente:

* config/DataInitializer.java satos demo y dejar main limpio
* Input para añadir imagenes
* OrderService para lógica de pedidos
* spring validation para formularios para validar datos de entrada
* Controllador API REST @RestController y probarlo con OpenAPI Swagger / Postman
* Test de un controllador con MockMvc
* Presentación slides para explicar el proyecto: google slides, canva, powerpoint,




Subir imagenes:


* User,Tienda, Producto: private String imageUrl; // URL de la imagen en el servidor o servicio de almacenamiento
* service/FileStoreageService.java
* config/WebConfig.java
* Actualizar en SecurityConfig añadir ruta /uploads/**
* user-form.html, tienda-form.html añadir un input de tipo file
* En todos lo comtroller agrega multipartFile imageFile
* Mostrar imahgen en la aplicacion: <img th:src="@{${user.imageUrl}}" alt="User Image" />