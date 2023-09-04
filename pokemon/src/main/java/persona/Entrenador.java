package persona;

import criatura.Pokemon;
import lombok.Getter;
import lombok.Setter;
import poderes.Ataque;

import java.util.ArrayList;
import java.util.Random;

@Setter @Getter
public class Entrenador {

    private ArrayList<Pokemon> pokebola;

    public Entrenador() {
        pokebola = new ArrayList<>();
    }

    public void atrapar(Pokemon pokemon) {
        if (new Random().nextBoolean()) {
            pokebola.add(pokemon);
            System.out.println("Ash capturó a " + pokemon);
        } else
            System.out.println("Ash no atrapó a " + pokemon);
    }

    public ArrayList<Pokemon> entrenar(Ataque ataque) {
        for (Pokemon pokemon : pokebola) {
            pokemon.aprender(ataque);
            System.out.printf("Entrenando a %s con %s...%n", pokemon, ataque);
        }
        return pokebola;
    }

}
