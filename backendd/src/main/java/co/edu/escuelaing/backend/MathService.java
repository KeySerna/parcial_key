package co.edu.escuelaing.backend;

import java.math.BigInteger;
import org.springframework.stereotype.Service;

// @Service le dice a Spring: "crea un objeto de esta clase
// y dáselo a quien lo necesite".
@Service
public class MathService {

    // Revisa si una palabra se lee igual al derecho y al revés.
    // Ignora mayúsculas y espacios: "Anita lava la tina" -> true
    public boolean isPalindrome(String text) {
        String clean = text.replace(" ", "").toLowerCase();

        // Dos posiciones: una al inicio y otra al final, que se acercan al centro
        int left = 0;
        int right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Calcula n! = 1 x 2 x 3 x ... x n
    // BigInteger porque con int el resultado sale mal a partir de 13!
    public BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El número no puede ser negativo");
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}