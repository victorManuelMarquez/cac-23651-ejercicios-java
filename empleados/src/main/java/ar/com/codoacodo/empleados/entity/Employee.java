package ar.com.codoacodo.empleados.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Data
public class Employee {

    @PositiveOrZero
    private Integer id;

    @NotBlank
    @Size(max = 9, message = "Dni demasiado largo.")
    private String dni;

    @NotBlank
    @Size(max = 60, message = "El nombre es demasiado largo.")
    private String nombre;

    @NotBlank
    @Size(max = 70, message = "El apellido es demasiado largo.")
    private String apellido;

    @Past
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
