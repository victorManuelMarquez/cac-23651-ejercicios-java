package ar.com.codoacodo.multicapa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

    private Integer id;
    private String nombre, apellido;

}
