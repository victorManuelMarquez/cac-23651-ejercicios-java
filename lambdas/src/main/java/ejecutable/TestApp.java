package ejecutable;

import individuo.Persona;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestApp {

    public static void main(String[] args) {
        String[] nombres = {
                "Emily Johnson",
                "Alexander Smith",
                "Sophia Rodriguez",
                "Liam Thompson",
                "Olivia Davis",
                "Benjamin Wilson",
                "Ava Martinez",
                "Noah Anderson",
                "Mia Garcia",
                "James Brown"
        };
        List<String> listaNombres = List.of(nombres);
        Supplier<Persona> nuevaPersona = getPersonaSupplier(nombres);
        ArrayList<Persona> personas = new ArrayList<>();
        listaNombres.forEach(v -> personas.add(nuevaPersona.get()));
        personas.forEach(System.out::println);
    }

    private static Supplier<Persona> getPersonaSupplier(String[] nombres) {
        Supplier<Random> nuevoRandom = Random::new;
        Function<String[], String> nombreAleatorio = arr -> arr[nuevoRandom.get().nextInt(arr.length)];
        BiFunction<Integer, Integer, String> numeroRandom = (min, max) -> ((Integer) nuevoRandom.get().nextInt(min, max)).toString();
        BiFunction<Long, Long, Date> fechaRandom = (f,h) -> new Date(ThreadLocalRandom.current().nextLong(f, h));
        long inicio = LocalDate.of(1980, 1, 1).toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC);
        long fin = LocalDate.of(2000, 1, 1).toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC);
        return () -> new Persona(
                numeroRandom.apply(20000000, 40000001),
                nombreAleatorio.apply(nombres),
                fechaRandom.apply(
                        Instant.ofEpochSecond(inicio).toEpochMilli(),
                        Instant.ofEpochSecond(fin).toEpochMilli()
                )
        );
    }

}
