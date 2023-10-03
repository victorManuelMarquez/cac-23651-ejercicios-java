package ar.com.codoacodo.concesionaria.repository;

import ar.com.codoacodo.concesionaria.dto.CarServiceDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class CarRepository {

    @SuppressWarnings("FieldMayBeFinal")
    private List<CarServiceDto> cars = new ArrayList<>();

    public CarServiceDto agregar(CarServiceDto vehicle) {
        cars.add(vehicle);
        return vehicle;
    }

    public List<CarServiceDto> listar() {
        return cars;
    }

    public List<CarServiceDto> listarPorFecha(LocalDate since, LocalDate to) {
        Predicate<CarServiceDto> rango = car -> car.getManufacturingDate().isAfter(since) && car.getManufacturingDate().isBefore(to);
        return cars.stream().filter(rango).collect(Collectors.toList());
    }

    public List<CarServiceDto> listarPorPrecio(Integer since, Integer to) {
        Predicate<CarServiceDto> rango = car -> car.getPrice() >= since && car.getPrice() <= to;
        return cars.stream().filter(rango).collect(Collectors.toList());
    }

    public Optional<CarServiceDto> detalles(Integer index) {
        CarServiceDto dto = null;
        try {
            dto = cars.get(index);
        } catch (IndexOutOfBoundsException e) {
            // ignore
        }
        return Optional.ofNullable(dto);
    }

}
