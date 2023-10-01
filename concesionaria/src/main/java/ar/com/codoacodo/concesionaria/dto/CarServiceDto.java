package ar.com.codoacodo.concesionaria.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class CarServiceDto extends CarDto {

    private List<ServiceDto> services;

    @Builder
    public CarServiceDto(String brand, String model, String currency, Integer numberOfKilometers, Integer doors, Integer price, Integer countOfOwners, Date manufacturingDate, List<ServiceDto> services) {
        super(brand, model, currency, numberOfKilometers, doors, price, countOfOwners, manufacturingDate);
        this.services = services;
    }

    public static CarDto toCarDto(CarServiceDto serviceDto) {
        return new CarDto(
                serviceDto.getBrand(),
                serviceDto.getModel(),
                serviceDto.getCurrency(),
                serviceDto.getNumberOfKilometers(),
                serviceDto.getDoors(),
                serviceDto.getPrice(),
                serviceDto.getCountOfOwners(),
                serviceDto.getManufacturingDate()
        );
    }

}
