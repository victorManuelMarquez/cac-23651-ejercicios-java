package ejecutable;

import consumible.animal.*;
import consumible.Comestible;
import consumible.vegetal.Vegetal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Se pone a prueba el modelo o solución al planteo de clases abstractas e interfaces.
 * @author victor
 * @version 2.0
 */
public class Programa {

    // Se requiere de Java 17 o posterior
    public static void main(String[] args) {
        List<Animal> animales = generarLista(List.of(
                new Perro(), new Gato(), new Vaca()
        ), 5);
        System.out.println("Animales:");
        for (Animal animal : animales)
            System.out.println(animal);
        List<Comestible> alimentos = generarLista(List.of(
                new Vaca(),
                (Vegetal) () -> "Hierba",
                (Vegetal) () -> "Mix de forraje",
                (Vegetal) () -> "Heno",
                (Vegetal) () -> "Trigo"
        ), 7);
        System.out.println("Hora de comer!!!");
        for (Comestible comida : alimentos) {
            for (Animal animal : animales) {
                System.out.printf("%s -> ", animal.emiteSonido());
                animal.comer(comida);
            }
        }
        System.out.println("Fin del programa.");
    }

    /**
     * Método genérico para crear una lista con los datos suministrados.
     *
     * @param datos    los datos base para crear la lista.
     * @param cantidad longitud de la lista.
     * @return una lista con <i>n</i> cantidad de items.
     * @param <T> tipo de dato base.
     */
    static <T> List<T> generarLista(List<T> datos, int cantidad) {
        if (cantidad <= 0)
            throw new IllegalArgumentException("rango inválido.");
        List<T> lista = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<cantidad; i++) {
            lista.add(datos.get(random.nextInt(datos.size())));
        }
        return lista;
    }

}
