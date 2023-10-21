package ar.com.codoacodo.empleados.exception;

public class EmployeeNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Empleado no encontrado.";
    }

}
