package ar.com.codoacodo.personasv4.exception.controller;

import ar.com.codoacodo.personasv4.exception.DuplicatedPersonException;
import ar.com.codoacodo.personasv4.exception.PersonNotFoundException;
import ar.com.codoacodo.personasv4.exception.SavingPersonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionController {

    @ExceptionHandler(DuplicatedPersonException.class)
    public ResponseEntity<String> duplicatedData(DuplicatedPersonException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SavingPersonException.class)
    public ResponseEntity<String> savingPersonFailed(SavingPersonException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> personNotFound(PersonNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
