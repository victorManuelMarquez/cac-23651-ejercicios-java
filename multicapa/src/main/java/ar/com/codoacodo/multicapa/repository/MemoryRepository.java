package ar.com.codoacodo.multicapa.repository;

import java.util.List;

public interface MemoryRepository<T, ID> {

    T guardar(T entidad);

    T buscarPorId(ID id);

    List<T> listar();

    T actualizar(T entidad);

    T borrar(ID id);

}
