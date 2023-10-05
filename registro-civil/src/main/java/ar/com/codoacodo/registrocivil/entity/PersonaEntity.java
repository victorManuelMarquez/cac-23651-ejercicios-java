package ar.com.codoacodo.registrocivil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaEntity {

    private Integer id;
    private String dni, nombres, apellidos, domicilio;
    private LocalDate fecha_nac;
    private List<PersonaEntity> padres;
    private List<PersonaEntity> hijos;
    private PersonaEntity pareja;
    private Boolean activo;

    public int edadActual() {
        return Period.between(getFecha_nac(), LocalDate.now()).getYears();
    }

    public boolean casado() {
        return getPareja() != null;
    }

}
