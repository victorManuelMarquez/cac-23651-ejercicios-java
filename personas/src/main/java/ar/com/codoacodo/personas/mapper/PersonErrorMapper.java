package ar.com.codoacodo.personas.mapper;

import ar.com.codoacodo.personas.bundle.PersonErrorDto;
import ar.com.codoacodo.personas.exception.PersonNotFoundException;

public class PersonErrorMapper {

    public static PersonErrorDto toPersonErrorDto(PersonNotFoundException e) {
        return new PersonErrorDto(
                e.getMessage(),
                e.getCause().getClass().getName()
        );
    }

}
