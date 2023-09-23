package juego;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Armadura implements Combate {

    private int piezas;

    @Override
    public String toString() {
        return String.format("Armadura de %d piezas.", piezas);
    }

    @Override
    public int unidadDeLucha() {
        return 10 + getPiezas() / 2;
    }

}
