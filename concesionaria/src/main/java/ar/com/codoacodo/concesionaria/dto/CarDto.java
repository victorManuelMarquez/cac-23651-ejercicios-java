package ar.com.codoacodo.concesionaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDto {

    private String brand, model, currency;

    private Integer numberOfKilometers, doors, price, countOfOwners;

    private LocalDate manufacturingDate;

}
