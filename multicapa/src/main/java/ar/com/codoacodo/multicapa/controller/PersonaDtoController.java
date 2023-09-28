package ar.com.codoacodo.multicapa.controller;

import ar.com.codoacodo.multicapa.dto.PersonaDto;
import ar.com.codoacodo.multicapa.service.ServicePersona;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/personas")
@SuppressWarnings("unused")
public class PersonaDtoController {

    private ServicePersona servicePersona;

    @PostMapping(value = "/nueva")
    public ResponseEntity<PersonaDto> nuevaPersona(@RequestBody PersonaDto persona) {
        PersonaDto agregado = servicePersona.crearPersona(persona);
        return new ResponseEntity<>(agregado, HttpStatus.CREATED);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<PersonaDto> buscarPersona(@PathVariable("id") Integer id) {
        PersonaDto encontrado = servicePersona.buscarPersonaPorId(id);
        return new ResponseEntity<>(encontrado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonaDto>> listarPersonas() {
        List<PersonaDto> list = servicePersona.todasLasPersonas();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/modificar/id={id}")
    public ResponseEntity<PersonaDto> modificarPersona(@PathVariable("id") Integer id, @RequestBody PersonaDto persona) {
        PersonaDto entidadPersona = servicePersona.buscarPersonaPorId(id);
        if (entidadPersona != null) {
            entidadPersona.setNombre(persona.getNombre());
            entidadPersona.setApellido(persona.getApellido());
            return new ResponseEntity<>(servicePersona.actualizarPersona(entidadPersona), HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/eliminar/id={id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable("id") Integer id) {
        PersonaDto eliminada = servicePersona.eliminarPersona(id);
        return new ResponseEntity<>(eliminada != null ? "eliminada : OK!" : "falló", HttpStatus.OK);
    }

}
