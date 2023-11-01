package ar.com.codoacodo.personasv3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("personaDto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaDto {

    @PositiveOrZero
    private Long id;

    @NotBlank
    @Size(max = 9, message = "El dni ingresado es demasiado largo.")
    private String dni;

    @NotBlank
    @Size(max = 60, message = "El nombre ingresado es demasiado largo.")
    private String nombre;

    @NotBlank
    @Size(max = 60, message = "El apellido ingresado es demasiado largo.")
    private String apellido;

    @Past
    private LocalDate fechaNac;

}
