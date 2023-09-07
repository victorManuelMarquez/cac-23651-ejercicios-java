package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DemoGenerico {

    public static void main(String[] args) {
        ArrayList<Integer> enteros = new ArrayList<>();
        ArrayList<Character> caracteres = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<random.nextInt(10, 21); i++) {
            enteros.add(random.nextInt(-100, 101));
            caracteres.add((char) (65 + random.nextInt(26)));
        }
        System.out.println("Origen: " + enteros);
        System.out.println("Origen: " + caracteres);
        Integer[] enterosOrdenados = ordenar(enteros.toArray(new Integer[0]));
        System.out.println("Ordenada: " + Arrays.toString(enterosOrdenados));
        Character[] caracteresOrdenados = ordenar(caracteres.toArray(new Character[0]));
        System.out.println("Ordenada: " + Arrays.toString(caracteresOrdenados));
    }

    static <T extends Comparable<T>> T[] ordenar(T[] lista) {
        for (int i=0; i<lista.length-1; i++) {
            for (int j=i+1; j<lista.length; j++) {
                if (lista[i].compareTo(lista[j]) > 0) {
                    T aux = lista[i];
                    lista[i] = lista[j];
                    lista[j] = aux;
                }
            }
        }
        return lista;
    }

}
