

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

* Proximamente
 * Stock (zapatos, camisas, sudaderas, complementos, busiteria)

 * order (Pedido)
 * long id
 * LocalDate orderDate
 * String marca
 * double price
 * String description
 * asociaciones
 * tiendas (ManyToOne)
 * user (ManyToOne)


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