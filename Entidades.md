

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


* Review
 * long id
 * String comment
 * Integer rating
 * LocalDate reviewDate
 * asociaciones
 * tiendas (ManyToOne)
 * user (ManyToOne)
 * 
 * 