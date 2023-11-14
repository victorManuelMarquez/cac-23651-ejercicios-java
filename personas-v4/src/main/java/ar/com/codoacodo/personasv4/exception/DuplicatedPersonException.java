package ar.com.codoacodo.personasv4.exception;

public class DuplicatedPersonException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Los datos coinciden con otra persona registrada.";
    }

}
