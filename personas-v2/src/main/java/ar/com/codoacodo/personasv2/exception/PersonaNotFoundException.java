package ar.com.codoacodo.personasv2.exception;

/**
 * Error al no obtener el registro de una persona.
 */
public class PersonaNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Esta persona no existe.";
    }

}
