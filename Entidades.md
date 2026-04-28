

* Tiendas
 * Long id
 * String nombre
 * double averagePrice
 * boolean active
 * FooType category (enum)
 * LocalDate startDate
 * Integer numberEmployees

* Employee
 * long id
 * String firstName
 * String lastName
 * String dni
 * Integer age
 * Tiendas tienda (ManyToOne) (NUEVO)

* ClothesType (enum)  
 + Classic
 + Streetwear


* Stock (Productos) 
 * Long id 
 * String nameProduct
 * String description
 * Double price
 * ProductType type (enum: zapatos, camisas, sudaderas, complementos, etc)
 * Tiendas tienda (ManyToOne) (Asociación)


* order (Pedido) (NUEVO)
 * Long id
 * LocalDateTime fecha
 * String marca
 * double totalPrice
 * String description
 * estado (enum: RECIBIDO, EN_PROCESO, ENVIADO, ENTREGADO, DEVUELTO, CANCELADO)
 * asociaciones
  * tiendas (ManyToOne)
  * user (ManyToOne)
  * List<Product> productos (ManyToMany)


* OrderLine 
 * Long id
 * Integer quantity
 * Asociaciones
    * Productos productos (ManyToOne)
    * order order (ManyToOne)


Pasos para el Controller:

* [ok] Crear paquete nuevo llamado controller en com.namartej
* Crear una clase nueva llamada TiendaController
  * Anotar la clase con @Controller
  * crear un constructor que reciba el servicio de Tiendas (TiendaService) 
  y lo inyecte en un atributo privado final
  * crear un metodo publico llamado findAll que reciba un Model y devuelva String
  * Anotar el metodo con @GetMapping("/tiendas")
  * entro del metodo, usar tiendasRepository.findAll() para obtener la lista de
  tiendas y guardarla en el Model con model.addAttribute("tiendas", tiendasRepository.findAll())
  * devolver el nombre de la vista (String) que se va a renderizar, por ejemplo "tiendas"




* Review
 * long id
 * String comment
 * Integer rating
 * LocalDate reviewDate
 * asociaciones
 * tiendas (ManyToOne)
 * user (ManyToOne)
 * 


* Bootstrap CSS
  * Añadir en pom.xml
  * bootstrap
  * webjars_locator-list
  * font-awesome
* Crear carpeta templates/layout
  * Head
  * navbar
  * footer
