package ar.com.codoacodo.personasv4.mapper;

import ar.com.codoacodo.personasv4.bean.Person;
import ar.com.codoacodo.personasv4.dto.request.SavePersonDto;
import ar.com.codoacodo.personasv4.dto.response.StoredPersonDto;
import ar.com.codoacodo.personasv4.util.LocalDateFormatter;

public class PersonMapper {

    public static StoredPersonDto toDto(Person value) {
        return new StoredPersonDto(
                value.getName(),
                value.getLastName(),
                LocalDateFormatter.localDateToString(value.getBirthDate())
        );
    }

    public static Person toBean(SavePersonDto dto) {
        return new Person(
                dto.getId(),
                dto.getPersonalId(),
                dto.getName(),
                dto.getLastName(),
                LocalDateFormatter.fromStringToLocalDate(dto.getBirthDate())
        );
    }

}
