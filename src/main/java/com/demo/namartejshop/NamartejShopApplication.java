package com.demo.namartejshop;

import com.demo.namartejshop.model.ClothesType;
import com.demo.namartejshop.model.Employee;
import com.demo.namartejshop.model.Tiendas;
import com.demo.namartejshop.repository.EmployeeRepository;
import com.demo.namartejshop.repository.TiendasRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Una clase con @ entity equivale a una tabla de base de datos
// Un objeto equivale a una fila en una tabla de base de datos
@SpringBootApplication
public class NamartejShopApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(NamartejShopApplication.class, args);

        // obtener repositorios para poder hacer operaciones de base de datos con ellos
        TiendasRepository tiendasRepository = context.getBean(TiendasRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

        // crear un objeto restaurante y guardarlo en la base de datos: new
        Tiendas namartej = new Tiendas();
        namartej.setName("Namartej Madrid");
        namartej.setAverageprice(29.5);
        namartej.setNumberEmployees(8);

        // guardar las tiendas en base de datos usando el repositorio: .save();
        tiendasRepository.save(namartej);

        // opción 1: crear un objeto vacio:
        Tiendas tien1 = new Tiendas();

        // Opción 2: crear un objeto con datos
        Tiendas tien2 = new Tiendas("Namartej Barcelona", 14.6, 5);
        tiendasRepository.save(tien2);

        // Opción 3:
        Tiendas tien3 = new Tiendas();
        tien3.setName("Namartej Valencia");
        tien2.setAverageprice(40.6);
        tiendasRepository.save(tien3);

        // Crear un empleado con parametros y guardarlo en la base de datos
        Employee numero1 = new Employee();
        numero1.setFirstName("Juan");
        numero1.setLastName("Perez");
        numero1.setAge(30);
        numero1.setDni("12345678A");
        employeeRepository.save(numero1);

        Employee numero2 = new Employee("Jose", "García", 27, "38470123B");
        employeeRepository.save(numero2);

        // Crear un empleado y guardarlo en base de datos
        Employee numero3 = new Employee();
        employeeRepository.save(numero3);

        // OBTENER todos los restaurantes de base de datos
        // SELECT * FROM TIENDAS;
        List<Tiendas> tiendas = tiendasRepository.findAll();
        System.out.println(tiendas);
        // for (int i = 0; i < tiendas.size(); i++) {
        //     System.out.println(tiendas.get(i));
        // }
        for (Tiendas tienda : tiendas) {  //bucle foreach para imprimir cada tienda por separado
            System.out.println(tienda);
        }

        // Aqui traigo datos de la tabla de employees
        List<Employee> employees = employeeRepository.findAll();
        System.out.println(employees);

        for (Employee empleado : employees) {  //bucle foreach para imprimir cada empleado por separado
            System.out.println(empleado);
        }

        // saveAll
        Tiendas t1 = new Tiendas("t1", 11.5, 6);
        Tiendas t2 = new Tiendas("t2", 15.0, 7);

        // Opción clasica para crear lista:
        List<Tiendas> tiendasTrending = new ArrayList<>(); // Crear una lista vacía
        tiendasTrending.add(t1); //Añadir una tienda a la lista
        tiendasTrending.add(t2); // añadir una tienda a la lista
        List<String> alumnos = new ArrayList<>(); // Crear una lista vacia
        List<Double> precios = new ArrayList<>(); // Crear una lista vacia

        //Opcion moderna para crear lista:
        List<Tiendas> sitiosGuaposParaComprar = List.of(t1,t2);
        tiendasRepository.saveAll(sitiosGuaposParaComprar);

        // count () para contar cuantas filas hay en la tabla:
        long numeroTiendas = tiendasRepository.count();
        if ( numeroTiendas > 0) {
            System.out.println("Hay " + numeroTiendas + " tiendas en la base de datos");
        } else {
            System.out.println("No hay tiendas en la base de datos");
        }

       //existById devuelve boolean
        boolean existencia = tiendasRepository.existsById(1L);
        if(existencia)
            System.out.println("Existe la tienda 1");
        else
            System.out.println("No existe la tienda 1");


        //deleteAll borrar todas las filas de la tabla
        // tiendasRepository.deleteAll();

        // deleteById borrar una fila indicando si id, 1, 2, 3
        tiendasRepository.deleteById(1L); // hard code
        //tiendasRepository.deleteById(tien1.getId()); // para llamar en este caso una tienda en especifica de las existentes

        // delete, borra pasando el objeto
        tiendasRepository.delete(t2);
        // tiendasRepository.deleteByName("t1"); // no funciona porque no existe un metodo deleteByName en el repositorio, solo deleteById


        // findById traer una tienda por su id, devuelve un Optional
        Long idABuscar = 2L;
        // var tiendaOptional = tiendasRepository.findById(idABuscar);
        Optional<Tiendas> tienda2Optional = tiendasRepository.findById(idABuscar);
        if (tienda2Optional.isPresent()){
            Tiendas tienda2 = tienda2Optional.get();
            System.out.println(tienda2);
        }

        //Crear una coleccion nueva
        Tiendas classicCollection = new Tiendas();
        classicCollection.setClothesType(ClothesType.classic);
        tiendasRepository.save(classicCollection);
        System.out.println(classicCollection);


        // Crear una colección de streetwear
        Tiendas streetColletion = new Tiendas();
        streetColletion.setClothesType(ClothesType.Streetwear);
        tiendasRepository.save(streetColletion);
        System.out.println(streetColletion);






    }


}
