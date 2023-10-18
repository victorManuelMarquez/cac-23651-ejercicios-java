package ar.com.codoacodo.personas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Person not found!")
public class PersonNotFoundException extends RuntimeException implements StatusException {

    public PersonNotFoundException() {
        super("Person not found!");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
