package ar.com.codoacodo.registrocivil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaDto {

    private String dni, nombres, apellidos, domicilio;
    private LocalDate fecha_nac;
    private Boolean activo;

}
