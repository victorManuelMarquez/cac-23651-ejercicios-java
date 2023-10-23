package ar.com.codoacodo.empleados.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class EmployeeDto {

    @PositiveOrZero
    private Integer id;

    @NotBlank
    @Size(min = 6, message = "Dni demasiado corto.")
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

}
