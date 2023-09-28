package ar.com.codoacodo.multicapa.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntidadPersona {

    private Integer id;
    private String nombre, apellido;

}
