package juego;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class MascaraOscura implements Combate {

    private int nivelDeOscuridad;

    @Override
    public String toString() {
        return String.format("Máscara de Oscuridad: nivel %d.", nivelDeOscuridad);
    }

    @Override
    public int unidadDeLucha() {
        return getNivelDeOscuridad() * 2;
    }

}
