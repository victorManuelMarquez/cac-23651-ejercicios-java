package ar.com.codoacodo.registrocivil.service;

import java.util.List;

public interface ServicePersona<E> {

    E crear(E element);

    List<E> listarTodo();

    E buscar(String dni);

    E modificar(String dni, E element);

    E borrar(String dni);

    int obtenerEdad(String dni);

    void agregarProgenitor(String dni, String padre);

    List<E> listarProgenitores(String dni);

    void agregarLazoFamiliar(String dni, String hijo);

    List<E> listarHijos(String dni);

    void asentarPareja(String dni1, String dni2);

    int generarPersonas(Integer cantidad);

    boolean estaCasado(String dni);

}
