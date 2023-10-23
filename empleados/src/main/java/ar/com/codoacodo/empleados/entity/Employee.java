package ar.com.codoacodo.empleados.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Data
public class Employee {

    private Integer id;
    private String dni, nombre, apellido;
    private LocalDate fechaNac;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Employee employee) {
            return employee.getDni().equalsIgnoreCase(getDni());
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, apellido, fechaNac);
    }

}
