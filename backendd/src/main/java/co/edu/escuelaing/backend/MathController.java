package co.edu.escuelaing.backend;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController: esta clase atiende peticiones HTTP y responde en JSON.
@RestController
public class MathController {

    private final MathService service;

    // Spring nos pasa el MathService automáticamente por el constructor
    public MathController(MathService service) {
        this.service = service;
    }

    // Atiende: /palindrome?value=reconocer
    @GetMapping("/palindrome")
    public Map<String, Object> palindrome(@RequestParam String value) {
        // Spring convierte el Map a JSON solito
        return Map.of(
                "operation", "palindrome",
                "input", value,
                "output", service.isPalindrome(value));
    }

    // Atiende: /factorial?value=5
    @GetMapping("/factorial")
    public ResponseEntity<Map<String, Object>> factorial(@RequestParam String value) {
        try {
            int n = Integer.parseInt(value.trim());
            return ResponseEntity.ok(Map.of(
                    "operation", "factorial",
                    "input", value,
                    "output", service.factorial(n).toString()));
        } catch (NumberFormatException e) {
            // Mandaron letras en vez de número -> error 400
            return ResponseEntity.badRequest().body(Map.of("error", "Debes enviar un número entero"));
        } catch (IllegalArgumentException e) {
            // Número negativo -> error 400 con el mensaje del servicio
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}