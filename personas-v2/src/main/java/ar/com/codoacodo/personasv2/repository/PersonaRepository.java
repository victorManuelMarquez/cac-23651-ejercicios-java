package ar.com.codoacodo.personasv2.repository;

import ar.com.codoacodo.personasv2.bean.Persona;
import ar.com.codoacodo.personasv2.exception.PersonaDuplicadaException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de las personas registradas.
 */
@Repository
@AllArgsConstructor
@Data
public class PersonaRepository {

    private List<Persona> personas;

    public Persona buscarPorId(Integer id) throws NullPointerException {
        try {
            return getPersonas().get(id);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public boolean agregar(Persona entidad) throws NullPointerException {
        if (personas.stream().anyMatch(p -> p.equals(entidad)))
            throw new PersonaDuplicadaException();
        entidad.setId(personas.isEmpty() ? 0 : personas.size() - 1);
        return personas.add(entidad);
    }

    public boolean actualizar(Persona entidad) throws NullPointerException {
        if (personas.remove(entidad.getId()) == null)
            return false;
        personas.add(entidad.getId(), entidad);
        return personas.contains(entidad);
    }

    public boolean eliminar(Integer id) throws NullPointerException {
        try {
            return personas.remove((int) id) != null;
        } catch (IndexOutOfBoundsException exception) {
            return false;
        }
    }

    public List<Persona> listarTodo() {
        return getPersonas();
    }

}
