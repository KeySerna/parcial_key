package co.edu.escuelaing.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Igual que en el backend: esta clase arranca la aplicación Spring Boot del proxy.
@SpringBootApplication
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }
}