package ar.com.codoacodo.personasv4.exception;

public class SavingPersonException extends RuntimeException {

    @Override
    public String getMessage() {
        return "No se pudo guardar a esta persona.";
    }

}
