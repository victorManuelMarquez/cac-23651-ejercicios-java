package ar.com.codoacodo.multicapa.service.impl;

import ar.com.codoacodo.multicapa.dto.PersonaDto;
import ar.com.codoacodo.multicapa.entity.EntidadPersona;
import ar.com.codoacodo.multicapa.mapper.MapperPersona;
import ar.com.codoacodo.multicapa.repository.PersonaRepositoryImpl;
import ar.com.codoacodo.multicapa.service.ServicePersona;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServicePersonaImpl implements ServicePersona {

    private PersonaRepositoryImpl repository;

    @Override
    public PersonaDto crearPersona(PersonaDto personaDto) {
        EntidadPersona entidad = MapperPersona.mapeoParaEntidadPersona(personaDto);
        EntidadPersona almacenada = repository.guardar(entidad);
        return almacenada != null ? MapperPersona.mapeoParaPersonaDto(almacenada) : null;
    }

    @Override
    public PersonaDto buscarPersonaPorId(Integer id) {
        EntidadPersona entidadPersona = repository.buscarPorId(id);
        return entidadPersona != null ? MapperPersona.mapeoParaPersonaDto(entidadPersona) : null;
    }

    @Override
    public List<PersonaDto> todasLasPersonas() {
        List<EntidadPersona> personas = repository.listar();
        return personas.stream().map(MapperPersona::mapeoParaPersonaDto).collect(Collectors.toList());
    }

    @Override
    public PersonaDto actualizarPersona(PersonaDto personaDto) {
        EntidadPersona existente = repository.buscarPorId(personaDto.getId());
        if (existente != null) {
            existente.setNombre(personaDto.getNombre());
            existente.setApellido(personaDto.getApellido());
            EntidadPersona actualizada = repository.actualizar(existente);
            return MapperPersona.mapeoParaPersonaDto(actualizada);
        } else return null;
    }

    @Override
    public PersonaDto eliminarPersona(Integer id) {
        EntidadPersona eliminada = repository.borrar(id);
        return eliminada != null ? MapperPersona.mapeoParaPersonaDto(eliminada) : null;
    }

}
