package ar.com.codoacodo.personasv3.controller;

import ar.com.codoacodo.personasv3.bean.Persona;
import ar.com.codoacodo.personasv3.dto.PersonaDto;
import ar.com.codoacodo.personasv3.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/personas-v3")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @GetMapping
    public ResponseEntity<List<PersonaDto>> index() {
        ObjectMapper mapper = new ObjectMapper();
        Stream<PersonaDto> stream = service.listarPersonas().stream().map(v -> mapper.convertValue(v, PersonaDto.class));
        return new ResponseEntity<>(stream.toList(), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<PersonaDto> agregar(@Valid @RequestBody PersonaDto dto) {
        ObjectMapper mapper = new ObjectMapper();
        Persona persona = mapper.convertValue(dto, Persona.class);
        return new ResponseEntity<>(mapper.convertValue(service.agregarPersona(persona), PersonaDto.class), HttpStatus.OK);
    }

}
