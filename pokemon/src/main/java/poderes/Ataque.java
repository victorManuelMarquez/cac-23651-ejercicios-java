package poderes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Ataque {

    private String nombre;
    private int potencia;

    @Override
    public String toString() {
        return String.format("%s (potencia %d)", nombre, potencia);
    }

}
