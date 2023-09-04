package ejecutable;

import criatura.Charizard;
import criatura.Pokemon;
import criatura.Psyduck;
import persona.Entrenador;
import poderes.Ataque;

import java.util.List;

public class TestApp {

    public static void main(String[] args) {
        Entrenador ash = new Entrenador();
        for (Pokemon pokemon : List.of(
                new Pokemon("Pikachu", new Ataque("agilidad", 1), new Ataque("trueno", 6), new Ataque("cola de hierro", 3)),
                new Charizard(),
                new Psyduck()
        )) {
            ash.atrapar(pokemon);
        }
        Ataque ataque = new Ataque("Mordisco", 2);
        for (Pokemon entrenado : ash.entrenar(ataque)) {
            System.out.println(entrenado + " nivel de ataque: " + entrenado.nivelDeAtaque());
            System.out.println("Ataques: " + entrenado.getAtaques());
        }
    }

}
