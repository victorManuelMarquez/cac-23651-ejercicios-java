package ar.com.codoacodo.multicapa.repository;

import ar.com.codoacodo.multicapa.entity.EntidadPersona;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepositoryImpl implements MemoryRepository<EntidadPersona, Integer> {

    @SuppressWarnings("FieldMayBeFinal")
    private List<EntidadPersona> database = new ArrayList<>();

    @Override
    public EntidadPersona guardar(EntidadPersona entidad) {
        if (database.contains(entidad))
            return null;
        else {
            database.add(entidad);
            return entidad;
        }
    }

    @Override
    public EntidadPersona buscarPorId(Integer id) {
        if (database.isEmpty())
            return null;
        else {
            Optional<EntidadPersona> optional = database.stream().filter(e -> e.getId().equals(id)).findAny();
            return optional.map(persona -> database.get(database.indexOf(persona))).orElse(null);
        }
    }

    @Override
    public List<EntidadPersona> listar() {
        return database;
    }

    @Override
    public EntidadPersona actualizar(EntidadPersona entidad) {
        if (database.isEmpty()) {
            return null;
        } else {
            if (database.contains(entidad)) {
                database.remove(entidad);
                database.add(entidad);
                return entidad;
            } else return null;
        }
    }

    @Override
    public EntidadPersona borrar(Integer id) {
        if (database.isEmpty())
            return null;
        else {
            EntidadPersona eliminar = buscarPorId(id);
            return eliminar != null ? database.remove(database.indexOf(eliminar)) : null;
        }
    }

}
