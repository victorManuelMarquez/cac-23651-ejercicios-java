package ar.com.codoacodo.concesionaria.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceDto {

    private Date date;

    private Integer kilometers;

    private String descriptions;

}
