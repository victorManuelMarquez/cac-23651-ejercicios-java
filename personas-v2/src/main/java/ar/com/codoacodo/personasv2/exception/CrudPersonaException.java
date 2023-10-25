package ar.com.codoacodo.personasv2.exception;

/**
 * Error en alguna operación de gestión.
 */
public class CrudPersonaException extends RuntimeException {

    public CrudPersonaException(String message) {
        super(message);
    }

}
