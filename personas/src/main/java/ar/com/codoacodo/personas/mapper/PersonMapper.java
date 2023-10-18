package ar.com.codoacodo.personas.mapper;

import ar.com.codoacodo.personas.bundle.PersonDto;
import ar.com.codoacodo.personas.entity.Person;

public class PersonMapper {

    private PersonMapper() {
        // ignored
    }

    public static PersonDto toDto(Person entity) {
        return new PersonDto(
                entity.getId(),
                entity.getPersonalId(),
                entity.getName(),
                entity.getLastName(),
                entity.getBirthdate()
        );
    }

    public static Person toEntity(PersonDto dto) {
        return new Person(
                dto.getId(),
                dto.getPersonalId(),
                dto.getName(),
                dto.getLastName(),
                dto.getBirthdate()
        );
    }

}
