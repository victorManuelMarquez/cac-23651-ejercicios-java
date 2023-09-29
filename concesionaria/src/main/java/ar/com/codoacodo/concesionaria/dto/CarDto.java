package ar.com.codoacodo.concesionaria.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDto {

    private String brand, model, currency;

    private Integer numberOfKilometers, doors, price, countOfOwners;

    private Date manufacturingDate;

    private List<CarServiceDto> services;

}
