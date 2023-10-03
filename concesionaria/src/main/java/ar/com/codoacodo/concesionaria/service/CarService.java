package ar.com.codoacodo.concesionaria.service;

import ar.com.codoacodo.concesionaria.dto.CarServiceDto;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    CarServiceDto agregar(CarServiceDto vehicle);

    List<CarServiceDto> listar();

    List<CarServiceDto> listarEntreFechas(LocalDate inicio, LocalDate fin);

    List<CarServiceDto> listarEntrePrecios(Integer inicio, Integer fin);

    CarServiceDto buscar(Integer index);

}
