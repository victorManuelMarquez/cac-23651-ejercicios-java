package ar.com.codoacodo.empleados.repository;

import java.util.List;
import java.util.Optional;

public interface BasicRepository<D> {

    void add(D data);

    Optional<D> remove(D data);

    Optional<D> update(D data);

    List<D> listAll();

}
