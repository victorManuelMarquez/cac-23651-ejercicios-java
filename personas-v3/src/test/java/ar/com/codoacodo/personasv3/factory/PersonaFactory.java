package ar.com.codoacodo.personasv3.factory;

import ar.com.codoacodo.personasv3.bean.Persona;

import java.time.LocalDate;
import java.util.Random;

public class PersonaFactory {

    protected static final String[] nombres = {
            "Ariel", "Ana", "José", "Esteban", "Facundo", "Nanci", "Julia", "Mario", "Fabian"
    }, apellidos = {
            "Nuñez", "Morales", "Rodríguez", "Ocampo", "Pérez", "Fernández", "Roldan"
    };

    public static Persona crearIndividuo() {
        Random semilla = new Random();
        return new Persona(
                semilla.nextLong(1L, 10000L),
                ((Integer) semilla.nextInt(19999999, 49999999)).toString(),
                nombres[semilla.nextInt(nombres.length)],
                apellidos[semilla.nextInt(apellidos.length)],
                fechaRandom(semilla)
        );
    }

    protected static LocalDate fechaRandom(Random random) {
        long inicio = LocalDate.of(1980, 1, 1).toEpochDay();
        long fin = LocalDate.of(2005, 1, 1).toEpochDay();
        return LocalDate.ofEpochDay(random.nextLong(inicio, fin));
    }

}
