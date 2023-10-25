package ar.com.codoacodo.personasv2.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Un DTO de la clase {@code Persona}.
 *
 * @see ar.com.codoacodo.personasv2.bean.Persona Persona
 */
@AllArgsConstructor
@Data
public class PersonaDto {

    @PositiveOrZero
    private Integer id;

    @NotBlank
    @Size(min = 6)
    @Size(max = 9)
    private String dni;

    @NotBlank
    @Size(max = 60)
    private String nombre;

    @NotBlank
    @Size(max = 60)
    private String apellido;

    @NotBlank
    @Size(max = 140)
    private String domicilio;

    @NotNull
    private List<String> emails;

    @NotNull
    private List<String> contactos;

    @Past
    private LocalDate fechaNac;

    @NotNull
    private Boolean activo;

}
