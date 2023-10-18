package ar.com.codoacodo.personas.controller;

import ar.com.codoacodo.personas.bundle.PersonErrorDto;
import ar.com.codoacodo.personas.exception.PersonNotFoundException;
import ar.com.codoacodo.personas.mapper.PersonErrorMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@SuppressWarnings("unused")
public class PersonExceptionController {

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<PersonErrorDto> personNotFound(PersonNotFoundException e) {
        return new ResponseEntity<>(PersonErrorMapper.toPersonErrorDto(e), e.getStatus());
    }

}
