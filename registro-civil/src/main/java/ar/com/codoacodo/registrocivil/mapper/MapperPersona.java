package ar.com.codoacodo.registrocivil.mapper;

import ar.com.codoacodo.registrocivil.dto.PersonaDto;
import ar.com.codoacodo.registrocivil.entity.PersonaEntity;

public class MapperPersona {

    public static PersonaEntity toPersonaEntity(PersonaDto dto) {
        return new PersonaEntity(
                0,
                dto.getDni(),
                dto.getNombres(),
                dto.getApellidos(),
                dto.getDomicilio(),
                dto.getFecha_nac(),
                null,
                null,
                null,
                dto.getActivo()
        );
    }

    public static PersonaDto toPersonaDto(PersonaEntity entity) {
        return new PersonaDto(
                entity.getDni(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getDomicilio(),
                entity.getFecha_nac(),
                entity.getActivo()
        );
    }

}
