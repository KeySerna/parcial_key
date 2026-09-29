package co.edu.escuelaing.backend;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Clase de pruebas: cada método con @Test es una prueba independiente.
public class MathServiceTest {

    // Creamos el servicio a mano; no necesitamos encender Spring
    private final MathService service = new MathService();

    // ---------- Palíndromos ----------

    @Test
    public void palabraPalindromaDebeDarTrue() {
        // assertTrue: "espero que esto sea verdadero"
        assertTrue(service.isPalindrome("reconocer"));
    }

    @Test
    public void palabraNoPalindromaDebeDarFalse() {
        // assertFalse: "espero que esto sea falso"
        assertFalse(service.isPalindrome("hola"));
    }

    @Test
    public void debeIgnorarEspaciosYMayusculas() {
        assertTrue(service.isPalindrome("Anita lava la tina"));
    }

    // ---------- Factorial ----------

    @Test
    public void factorialDeCincoDebeSer120() {
        // assertEquals(esperado, obtenido)
        assertEquals(BigInteger.valueOf(120), service.factorial(5));
    }

    @Test
    public void factorialDeCeroDebeSerUno() {
        assertEquals(BigInteger.ONE, service.factorial(0));
    }

    @Test
    public void factorialGrandeNoDebeDesbordarse() {
        assertEquals(new BigInteger("15511210043330985984000000"), service.factorial(25));
    }

    @Test
    public void factorialNegativoDebeLanzarError() {
        // assertThrows: "espero que esto lance este error"
        assertThrows(IllegalArgumentException.class, () -> service.factorial(-3));
    }
}
