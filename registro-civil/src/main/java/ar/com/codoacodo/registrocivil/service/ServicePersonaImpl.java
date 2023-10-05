package ar.com.codoacodo.registrocivil.service;

import ar.com.codoacodo.registrocivil.dto.PersonaDto;
import ar.com.codoacodo.registrocivil.entity.PersonaEntity;
import ar.com.codoacodo.registrocivil.mapper.MapperPersona;
import ar.com.codoacodo.registrocivil.repository.RepositoryPersona;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicePersonaImpl implements ServicePersona<PersonaDto> {

    private RepositoryPersona repository;

    @Override
    public PersonaDto crear(PersonaDto element) {
        return MapperPersona.toPersonaDto(repository.agregar(MapperPersona.toPersonaEntity(element)));
    }

    @Override
    public List<PersonaDto> listarTodo() {
        return repository.listar().stream().map(MapperPersona::toPersonaDto).toList();
    }

    @Override
    public PersonaDto buscar(String dni) {
        Optional<PersonaEntity> entity = repository.buscarPorDni(dni);
        return entity.map(MapperPersona::toPersonaDto).orElse(null);
    }

    @Override
    public PersonaDto modificar(String dni, PersonaDto element) {
        return MapperPersona.toPersonaDto(repository.actualizar(dni, MapperPersona.toPersonaEntity(element)));
    }

    @Override
    public PersonaDto borrar(String dni) {
        return MapperPersona.toPersonaDto(repository.borrar(dni));
    }

    @Override
    public int obtenerEdad(String dni) {
        Optional<PersonaEntity> entity = repository.buscarPorDni(dni);
        return entity.map(PersonaEntity::edadActual).orElse(-1);
    }

    @Override
    public void agregarProgenitor(String dni, String padre) {
        repository.agregarPadre(dni, padre);
    }

    @Override
    public List<PersonaDto> listarProgenitores(String dni) {
        return repository.padres(dni).stream().map(MapperPersona::toPersonaDto).toList();
    }

    @Override
    public void agregarLazoFamiliar(String dni, String hijo) {
        repository.agregarHijo(dni, hijo);
    }

    @Override
    public List<PersonaDto> listarHijos(String dni) {
        return repository.hijos(dni).stream().map(MapperPersona::toPersonaDto).toList();
    }

    @Override
    public void asentarPareja(String dni, String pareja) {
        repository.casar(dni, pareja);
    }

    @Override
    public int generarPersonas(Integer cantidad) {
        return repository.crearPersonas(cantidad);
    }

    @Override
    public boolean estaCasado(String dni) {
        Optional<PersonaEntity> entity = repository.buscarPorDni(dni);
        return entity.isPresent() && entity.get().casado();
    }

}
