package ar.com.codoacodo.concesionaria.service;

import ar.com.codoacodo.concesionaria.dto.CarDto;

import java.util.Date;
import java.util.List;

public interface CarService {

    CarDto agregar(CarDto vehicle);

    List<CarDto> listar();

    List<CarDto> listarEntreFechas(Date inicio, Date fin);

    List<CarDto> listarEntrePrecios(Integer inicio, Integer fin);

    CarDto buscar(Integer index);

}
