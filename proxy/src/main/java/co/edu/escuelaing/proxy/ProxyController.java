package co.edu.escuelaing.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@RestController
public class ProxyController {

    // Lista de backends, leída de application.properties (propiedad "backends")
    private final String[] backends;

    // RestClient: la herramienta de Spring para hacer peticiones HTTP a otro servidor
    private final RestClient client = RestClient.create();

    // @Value toma el valor de la propiedad "backends" y lo separa por comas
    public ProxyController(@Value("${backends}") String[] backends) {
        this.backends = backends;
    }

    // El navegador llama: /palindrome?value=oso  -> lo reenviamos
    @GetMapping("/palindrome")
    public ResponseEntity<String> palindrome(@RequestParam String value) {
        return forward("/palindrome", value);
    }

    // El navegador llama: /factorial?value=5  -> lo reenviamos
    @GetMapping("/factorial")
    public ResponseEntity<String> factorial(@RequestParam String value) {
        return forward("/factorial", value);
    }

    // Recorre los backends en orden hasta que uno responda (tolerancia a fallos)
    private ResponseEntity<String> forward(String path, String value) {
        for (String backend : backends) {
            try {
                // Arma la URL, por ejemplo: http://localhost:45000/factorial?value=5
                // {v} se reemplaza por value y Spring lo codifica (espacios, tildes, etc.)
                String body = client.get()
                        .uri(backend.trim() + path + "?value={v}", value)
                        .retrieve()
                        .body(String.class);

                System.out.println("Respondió: " + backend);
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(body);

            } catch (RestClientResponseException e) {
                // El backend SÍ respondió, pero con error (ej. 400 por mandar "abc").
                // No es una caída, así que le pasamos ese error tal cual al usuario.
                return ResponseEntity.status(e.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e.getResponseBodyAsString());

            } catch (ResourceAccessException e) {
                // El backend NO respondió (apagado o inalcanzable) -> probamos el siguiente
                System.out.println("No respondió: " + backend + ", probando el siguiente...");
            }
        }
        // Ninguno respondió -> 503 Service Unavailable
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Ningún backend disponible\"}");
    }
}