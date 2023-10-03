package ar.com.codoacodo.concesionaria.service;

import ar.com.codoacodo.concesionaria.dto.CarServiceDto;
import ar.com.codoacodo.concesionaria.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    @Override
    public CarServiceDto agregar(CarServiceDto vehicle) {
        return repository.agregar(vehicle);
    }

    @Override
    public List<CarServiceDto> listar() {
        return repository.listar();
    }

    @Override
    public List<CarServiceDto> listarEntreFechas(LocalDate inicio, LocalDate fin) {
        return repository.listarPorFecha(inicio, fin);
    }

    @Override
    public List<CarServiceDto> listarEntrePrecios(Integer inicio, Integer fin) {
        return repository.listarPorPrecio(inicio, fin);
    }

    @Override
    public CarServiceDto buscar(Integer index) {
        Optional<CarServiceDto> optional = repository.detalles(index);
        return optional.orElse(null);
    }

}
