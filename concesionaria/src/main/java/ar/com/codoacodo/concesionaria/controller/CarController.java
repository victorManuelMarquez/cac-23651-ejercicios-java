package ar.com.codoacodo.concesionaria.controller;

import ar.com.codoacodo.concesionaria.dto.CarDto;
import ar.com.codoacodo.concesionaria.dto.CarServiceDto;
import ar.com.codoacodo.concesionaria.dto.ServiceDto;
import ar.com.codoacodo.concesionaria.service.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/api/vehicles")
@AllArgsConstructor
@SuppressWarnings("unused")
public class CarController {

    private CarServiceImpl service;

    @PostMapping
    public ResponseEntity<CarDto> addVehicle(@RequestBody CarServiceDto vehicle) {
        return new ResponseEntity<>(service.agregar(vehicle), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> listVehicles() {
        List<CarDto> lista = service.listar().stream().map(CarServiceDto::toCarDto).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping(value = "/dates")
    public ResponseEntity<List<CarServiceDto>> listByManufacturingDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate since,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return new ResponseEntity<>(service.listarEntreFechas(since, to), HttpStatus.OK);
    }

    @GetMapping(value = "/prices")
    public ResponseEntity<List<CarServiceDto>> listByPrices(@RequestParam Integer since, @RequestParam Integer to) {
        return new ResponseEntity<>(service.listarEntrePrecios(since, to), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarServiceDto> details(@PathVariable("id") Integer index) {
        return new ResponseEntity<>(service.buscar(index), HttpStatus.OK);
    }

    @GetMapping(value = "/generar")
    public ResponseEntity<String> generar() {
        Map<String, List<String>> marcaModelos = Map.ofEntries(
                Map.entry("Audi", List.of("A4", "A6", "TT", "Q7", "A3")),
                Map.entry("Ford", List.of("F-150", "Mustang", "Explorer", "Focus")),
                Map.entry("Nissan", List.of("Pathfinder")),
                Map.entry("Toyota", List.of("Corolla", "4Runner", "Tacoma")),
                Map.entry("Chevrolet", List.of("Silverado", "Impala", "Malibu", "Corsa"))
        );
        String[] currencies = {"ARS", "BOB", "BRL", "CLP", "COP", "USD", "GYD", "PYG", "PEN", "SRD", "UYU", "VES"};
        String[] descriptions = {
                "Oil Change",
                "Tire Rotation",
                "Brake Service",
                "Wheel Alignment",
                "Battery Replacement",
                "Air Filter Replacement",
                "Transmission Service",
                "Coolant Flush",
                "Spark Plug Replacement"
        };
        Random random = new Random();
        for (String marca : marcaModelos.keySet()) {
            int modelBound = marcaModelos.get(marca).size();
            String modelo = marcaModelos.get(marca).get(random.nextInt(modelBound));
            String currency = currencies[random.nextInt(currencies.length)];
            long inicio = LocalDate.of(2000, 1, 1).toEpochDay();
            long fin = LocalDate.of(2010, 12, 12).toEpochDay();
            LocalDate manufacturingDate = LocalDate.ofEpochDay(random.nextLong(inicio, fin));
            List<ServiceDto> services = new ArrayList<>();
            for (int i=0; i<random.nextInt(5); i++) {
                services.add(new ServiceDto(
                        LocalDate.now(),
                        random.nextInt(100000),
                        descriptions[random.nextInt(descriptions.length)]
                ));
            }
            service.agregar(new CarServiceDto(
                    marca,
                    modelo,
                    currency,
                    random.nextInt(200000), // km
                    random.nextInt(3, 6), // doors
                    random.nextInt(10000, 1000000), // price
                    random.nextInt(1, 3), // owners
                    manufacturingDate,
                    services
            ));
        }
        return new ResponseEntity<>(service.listar().size() + " vehicles added.", HttpStatus.OK);
    }

}
