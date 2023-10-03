package ar.com.codoacodo.concesionaria.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceDto {

    private LocalDate date;

    private Integer kilometers;

    private String descriptions;

}
