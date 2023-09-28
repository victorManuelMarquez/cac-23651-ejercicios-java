package ar.com.codoacodo.multicapa.service;

import ar.com.codoacodo.multicapa.dto.PersonaDto;

import java.util.List;

public interface ServicePersona {

    PersonaDto crearPersona(PersonaDto personaDto);

    PersonaDto buscarPersonaPorId(Integer id);

    List<PersonaDto> todasLasPersonas();

    PersonaDto actualizarPersona(PersonaDto personaDto);

    PersonaDto eliminarPersona(Integer id);

}
