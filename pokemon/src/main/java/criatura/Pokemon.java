package criatura;

import lombok.Getter;
import lombok.Setter;
import poderes.Ataque;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class Pokemon {

    private String nombre;
    private ArrayList<Ataque> ataques;

    public Pokemon(String nombre, Ataque... ataque) {
        this.nombre = nombre;
        ataques = new ArrayList<>(List.of(ataque));
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void aprender(Ataque ataque) {
        ataques.add(ataque);
    }

    public int nivelDeAtaque() {
        int nivel = 0;
        for (Ataque ataque : ataques)
            nivel += ataque.getPotencia();
        return nivel;
    }

}
