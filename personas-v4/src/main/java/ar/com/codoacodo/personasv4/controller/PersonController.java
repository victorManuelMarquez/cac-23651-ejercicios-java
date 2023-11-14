package ar.com.codoacodo.personasv4.controller;

import ar.com.codoacodo.personasv4.dto.request.SavePersonDto;
import ar.com.codoacodo.personasv4.dto.response.StoredPersonDto;
import ar.com.codoacodo.personasv4.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/personas-v4")
public class PersonController {

    private PersonService service;

    @GetMapping
    public ResponseEntity<List<StoredPersonDto>> index() {
        return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<StoredPersonDto> savePerson(@RequestBody @Valid SavePersonDto dto) {
        return new ResponseEntity<>(service.savePerson(dto), HttpStatus.OK);
    }

    @GetMapping("/registro")
    public ResponseEntity<StoredPersonDto> findPersonById(@RequestParam Long id) {
        return new ResponseEntity<>(service.findPersonById(id), HttpStatus.OK);
    }

}
