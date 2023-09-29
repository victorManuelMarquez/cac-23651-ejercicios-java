package ar.com.codoacodo.concesionaria.repository;

import ar.com.codoacodo.concesionaria.dto.CarDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class CarRepository {

    @SuppressWarnings("FieldMayBeFinal")
    private List<CarDto> cars = new ArrayList<>();

    public CarDto agregar(CarDto vehicle) {
        cars.add(vehicle);
        return vehicle;
    }

    public List<CarDto> listar() {
        return cars;
    }

    public List<CarDto> listarPorFecha(Date since, Date to) {
        Predicate<CarDto> rango = car -> car.getManufacturingDate().compareTo(since) >= 0 && car.getManufacturingDate().compareTo(to) <= 0;
        return cars.stream().filter(rango).collect(Collectors.toList());
    }

    public List<CarDto> listarPorPrecio(Integer since, Integer to) {
        Predicate<CarDto> rango = car -> car.getPrice() >= since && car.getPrice() <= to;
        return cars.stream().filter(rango).collect(Collectors.toList());
    }

    public Optional<CarDto> detalles(Integer index) {
        CarDto dto = null;
        try {
            dto = cars.get(index);
        } catch (IndexOutOfBoundsException e) {
            // ignore
        }
        return Optional.ofNullable(dto);
    }

}
