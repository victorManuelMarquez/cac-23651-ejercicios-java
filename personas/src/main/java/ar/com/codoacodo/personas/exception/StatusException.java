package ar.com.codoacodo.personas.exception;

import org.springframework.http.HttpStatus;

public interface StatusException {

    HttpStatus getStatus();

}
