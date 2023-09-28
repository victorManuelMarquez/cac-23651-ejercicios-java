package ar.com.codoacodo.multicapa.mapper;

import ar.com.codoacodo.multicapa.dto.PersonaDto;
import ar.com.codoacodo.multicapa.entity.EntidadPersona;

public class MapperPersona {

    public static PersonaDto mapeoParaPersonaDto(EntidadPersona persona) {
        return new PersonaDto(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido()
        );
    }

    public static EntidadPersona mapeoParaEntidadPersona(PersonaDto personaDto) {
        return new EntidadPersona(
                personaDto.getId(),
                personaDto.getNombre(),
                personaDto.getApellido()
        );
    }

}
