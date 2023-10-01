package ar.com.codoacodo.concesionaria.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDto {

    private String brand, model, currency;

    private Integer numberOfKilometers, doors, price, countOfOwners;

    private Date manufacturingDate;
}
