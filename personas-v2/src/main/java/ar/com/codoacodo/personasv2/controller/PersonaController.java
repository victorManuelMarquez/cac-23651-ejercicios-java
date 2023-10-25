package ar.com.codoacodo.personasv2.controller;

import ar.com.codoacodo.personasv2.dto.PersonaDto;
import ar.com.codoacodo.personasv2.mapper.PersonaMapper;
import ar.com.codoacodo.personasv2.service.PersonaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar una persona.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/personas-v2")
public class PersonaController {

    private PersonaService service;

    @GetMapping
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Personas registradas: " + service.totalDePersonas(), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@Valid @RequestBody PersonaDto dto) {
        return new ResponseEntity<>(service.agregarPersona(PersonaMapper.dtoAEntidad(dto)), HttpStatus.OK);
    }

    @GetMapping("/internal")
    public ResponseEntity<PersonaDto> registro(@RequestParam Integer id) {
        return new ResponseEntity<>(service.buscarPorId(id, PersonaMapper::entidadADto), HttpStatus.OK);
    }

    @GetMapping("/persona")
    public ResponseEntity<PersonaDto> registroPorDni(@RequestParam String dni) {
        return new ResponseEntity<>(service.buscarPorDni(dni, PersonaMapper::entidadADto), HttpStatus.OK);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<String> modificarRegistro(@RequestParam Integer id, @Valid @RequestBody PersonaDto dto) {
        return new ResponseEntity<>(service.actualizarPersona(id, PersonaMapper.dtoAEntidad(dto)), HttpStatus.OK);
    }

    @PostMapping("/borrar")
    public ResponseEntity<String> borrarRegistro(@RequestParam Integer id) {
        return new ResponseEntity<>(service.eliminarPersona(id), HttpStatus.OK);
    }

    @GetMapping("/todo")
    public ResponseEntity<List<PersonaDto>> mostrarTodo() {
        return new ResponseEntity<>(service.listarPersonas(PersonaMapper::entidadADto), HttpStatus.OK);
    }

}
