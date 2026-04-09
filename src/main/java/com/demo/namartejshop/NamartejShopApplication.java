package com.demo.namartejshop;

import com.demo.namartejshop.model.Employee;
import com.demo.namartejshop.model.Tiendas;
import com.demo.namartejshop.repository.EmployeeRepository;
import com.demo.namartejshop.repository.TiendasRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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


    }


}
