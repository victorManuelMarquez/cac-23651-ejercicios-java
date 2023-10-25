package ar.com.codoacodo.personasv2.service;

import ar.com.codoacodo.personasv2.bean.Persona;
import ar.com.codoacodo.personasv2.exception.CrudPersonaException;
import ar.com.codoacodo.personasv2.exception.PersonaNotFoundException;
import ar.com.codoacodo.personasv2.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Esta clase suministra las operaciones necesarias para gestionar los datos.
 * <b>Nota</b>: se empleó genéricos en las funciones para simplificar las operaciones fuera de esta clase.
 */
@Service
@AllArgsConstructor
public class PersonaService {

    private PersonaRepository repositorio;

    /**
     * Busca en los registros si existe la persona con el identificador solicitado.
     * Si no está presente se lanzará una excepción.
     *
     * @param id      identificador
     * @param formato función que especifica el tipo de dato esperable
     * @return la persona convertida a un nuevo tipo
     * @param <R> nuevo tipo de dato
     */
    public <R> R buscarPorId(Integer id, Function<Persona, R> formato) {
        if (id == null || formato == null)
            throw new CrudPersonaException("Faltan datos para esta operación.");
        return formato.apply(Optional.ofNullable(repositorio.buscarPorId(id)).orElseThrow(PersonaNotFoundException::new));
    }

    /**
     * Busca el dni en los registros y si existe lo devuelve como un nuevo tipo de dato.
     * Lanzará una excepción en caso de no encontrarse a la persona.
     *
     * @param dni     dni
     * @param formato función para realizar la conversión
     * @return la persona encontrada en un nuevo tipo de dato
     * @param <R> nuevo tipo de dato
     */
    public <R> R buscarPorDni(String dni, Function<Persona, R> formato) {
        if (dni == null || formato == null)
            throw new CrudPersonaException("Faltan datos para esta operación.");
        Predicate<Persona> filtro = p -> p.getDni().equalsIgnoreCase(dni);
        Stream<R> resultados = repositorio.listarTodo().stream().filter(filtro).map(formato);
        return resultados.findFirst().orElseThrow(PersonaNotFoundException::new);
    }

    public String agregarPersona(Persona persona) {
        if (persona == null)
            throw new CrudPersonaException("Faltan datos para esta operación.");
        if (repositorio.agregar(persona))
            return "Registro agregado.";
        else throw new CrudPersonaException("No se pudo agregar a la persona.");
    }

    public String actualizarPersona(Integer id, Persona persona) {
        if (id == null || persona == null)
            throw new CrudPersonaException("Faltan datos para esta operación.");
        persona.setId(id);
        if (repositorio.actualizar(persona))
            return "Registro actualizado.";
        else throw new CrudPersonaException("No se actualizó el registro.");
    }

    public String eliminarPersona(Integer id) {
        if (id == null)
            throw new CrudPersonaException("Falta el identificador para esta operación.");
        if (repositorio.eliminar(id))
            return "Registro eliminado.";
        else throw new CrudPersonaException("Falló la eliminación del registro.");
    }

    public <R> List<R> listarPersonas(Function<Persona, R> formato) {
        return repositorio.listarTodo().stream().map(formato).toList();
    }

    public int totalDePersonas() {
        return repositorio.listarTodo().size();
    }

}
