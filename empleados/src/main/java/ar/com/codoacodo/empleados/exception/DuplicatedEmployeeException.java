package ar.com.codoacodo.empleados.exception;

public class DuplicatedEmployeeException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Este empleado ya está registrado.";
    }

}
