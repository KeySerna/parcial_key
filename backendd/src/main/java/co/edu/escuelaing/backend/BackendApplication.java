package co.edu.escuelaing.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta anotación le dice a Spring: "aquí empieza mi aplicación,
// busca en este paquete mis controladores y servicios".
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        // Arranca el servidor web embebido (Tomcat) y deja la app escuchando peticiones.
        SpringApplication.run(BackendApplication.class, args);
    }
}