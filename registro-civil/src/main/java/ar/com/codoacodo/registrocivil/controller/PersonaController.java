package ar.com.codoacodo.registrocivil.controller;

import ar.com.codoacodo.registrocivil.dto.CasamientoDto;
import ar.com.codoacodo.registrocivil.dto.PersonaDto;
import ar.com.codoacodo.registrocivil.dto.DniPersonaDto;
import ar.com.codoacodo.registrocivil.service.ServicePersonaImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registro-civil")
@AllArgsConstructor
@SuppressWarnings("unused")
public class PersonaController {

    private ServicePersonaImpl service;

    @GetMapping
    public ResponseEntity<List<PersonaDto>> todasLasPersonas() {
        return new ResponseEntity<>(service.listarTodo(), HttpStatus.OK);
    }

    @GetMapping(value = "/cargar")
    public ResponseEntity<String> generar(@RequestParam Integer total) {
        return new ResponseEntity<>(String.format("Se han cargado %d personas del registro.", service.generarPersonas(total)), HttpStatus.OK);
    }

    @PostMapping(value = "/cargar")
    public ResponseEntity<PersonaDto> agregar(@RequestBody PersonaDto dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<PersonaDto> buscar(@RequestParam String dni) {
        return new ResponseEntity<>(service.buscar(dni), HttpStatus.OK);
    }

    @PostMapping(value = "/casamiento")
    public ResponseEntity<String> casarPersonas(@RequestBody CasamientoDto dto) {
        service.asentarPareja(dto.getDniIndividuoUno(), dto.getDniIndividuoDos());
        boolean estadoUno = service.estaCasado(dto.getDniIndividuoUno());
        boolean estadoDos = service.estaCasado(dto.getDniIndividuoDos());
        String mensaje = estadoUno && estadoDos ? "Ahora %s y %s están casados." : "No se pudo concretar...";
        return new ResponseEntity<>(String.format(
                mensaje, dto.getDniIndividuoUno(), dto.getDniIndividuoDos()
        ), HttpStatus.OK);
    }

    @GetMapping(value = "/{dni}/estado-civil")
    public ResponseEntity<String> estadoCivil(@PathVariable(value = "dni") String dni) {
        String mensaje = service.estaCasado(dni) ? "Casado " : "Soltero ";
        mensaje += service.listarHijos(dni).isEmpty() ? "sin hijos." : "con hijos.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping(value = "/{dni}/modificar")
    public ResponseEntity<PersonaDto> actualizarPersona(
            @PathVariable(value = "dni") String dni,
            @RequestBody PersonaDto actualizado
    ) {
        return new ResponseEntity<>(service.modificar(dni, actualizado), HttpStatus.OK);
    }

    @GetMapping(value = "/{dni}/informar-deceso")
    public ResponseEntity<PersonaDto> informar(@PathVariable(value = "dni") String dni) {
        return new ResponseEntity<>(service.borrar(dni), HttpStatus.OK);
    }

    @PostMapping(value = "/{dni}/agregar-progenitor")
    public ResponseEntity<List<PersonaDto>> agregarProgenitor(
            @PathVariable(value = "dni") String dni,
            @RequestBody DniPersonaDto progenitor
            ) {
        service.agregarProgenitor(dni, progenitor.getDni());
        return padresRegistrados(dni);
    }

    @GetMapping(value = "/{dni}/padres")
    public ResponseEntity<List<PersonaDto>> padresRegistrados(@PathVariable(value = "dni") String dni) {
        return new ResponseEntity<>(service.listarProgenitores(dni), HttpStatus.OK);
    }

    @PostMapping(value = "/{dni}/agregar-hijo")
    public ResponseEntity<List<PersonaDto>> agregarHijo(
            @PathVariable(value = "dni") String dni,
            @RequestBody DniPersonaDto hijo
    ) {
        service.agregarLazoFamiliar(dni, hijo.getDni());
        return hijosRegistrados(dni);
    }

    @GetMapping(value = "/{dni}/hijos")
    public ResponseEntity<List<PersonaDto>> hijosRegistrados(@PathVariable(value = "dni") String dni) {
        return new ResponseEntity<>(service.listarHijos(dni), HttpStatus.OK);
    }

    @GetMapping(value = "/{dni}/edad-actual")
    public ResponseEntity<Integer> edadActual(@PathVariable(value = "dni") String dni) {
        return new ResponseEntity<>(service.obtenerEdad(dni), HttpStatus.OK);
    }

}
