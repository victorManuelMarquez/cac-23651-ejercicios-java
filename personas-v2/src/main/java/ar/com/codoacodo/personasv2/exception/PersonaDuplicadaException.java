package ar.com.codoacodo.personasv2.exception;

/**
 * Error al ya contar con el registro almacenado.
 */
public class PersonaDuplicadaException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Esta persona ya está registrada.";
    }

}
