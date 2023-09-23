package juego;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Collar implements Combate {

    private int perlas;

    @Override
    public String toString() {
        return String.format("Collar de %d perlas.", perlas);
    }

    @Override
    public int unidadDeLucha() {
        return getPerlas();
    }

}
