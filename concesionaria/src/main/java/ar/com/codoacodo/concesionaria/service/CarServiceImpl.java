package ar.com.codoacodo.concesionaria.service;

import ar.com.codoacodo.concesionaria.dto.CarDto;
import ar.com.codoacodo.concesionaria.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    @Override
    public CarDto agregar(CarDto vehicle) {
        return repository.agregar(vehicle);
    }

    @Override
    public List<CarDto> listar() {
        return repository.listar();
    }

    @Override
    public List<CarDto> listarEntreFechas(Date inicio, Date fin) {
        return repository.listarPorFecha(inicio, fin);
    }

    @Override
    public List<CarDto> listarEntrePrecios(Integer inicio, Integer fin) {
        return repository.listarPorPrecio(inicio, fin);
    }

    @Override
    public CarDto buscar(Integer index) {
        Optional<CarDto> optional = repository.detalles(index);
        return optional.orElse(null);
    }

}
