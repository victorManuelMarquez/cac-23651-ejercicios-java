package juego;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Espada implements Combate {

    private int potencia;

    @Override
    public String toString() {
        return String.format("Espada: %d puntos de daño.", potencia);
    }

    @Override
    public int unidadDeLucha() {
        return getPotencia();
    }

}
