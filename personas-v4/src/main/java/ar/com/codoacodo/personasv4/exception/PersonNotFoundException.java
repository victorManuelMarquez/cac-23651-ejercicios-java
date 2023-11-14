package ar.com.codoacodo.personasv4.exception;

public class PersonNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "La persona solicitada no existe.";
    }

}
