package ar.com.codoacodo.personasv2.mapper;

import ar.com.codoacodo.personasv2.bean.Persona;
import ar.com.codoacodo.personasv2.dto.PersonaDto;

/**
 * Permite convertir una entidad en un elemento DTO y viceversa.
 */
public class PersonaMapper {

    /**
     * Convierte a entidad.
     *
     * @param dto DTO a convertir.
     * @return entidad.
     */
    public static Persona dtoAEntidad(PersonaDto dto) {
        return new Persona(
                dto.getId(),
                dto.getDni(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getDomicilio(),
                dto.getEmails(),
                dto.getContactos(),
                dto.getFechaNac(),
                dto.getActivo()
        );
    }

    /**
     * Convierte a DTO.
     *
     * @param entidad entidad a convertir.
     * @return dto.
     */
    public static PersonaDto entidadADto(Persona entidad) {
        return new PersonaDto(
                entidad.getId(),
                entidad.getDni(),
                entidad.getNombre(),
                entidad.getApellido(),
                entidad.getDomicilio(),
                entidad.getEmails(),
                entidad.getContactos(),
                entidad.getFechaNac(),
                entidad.isActivo()
        );
    }

}
