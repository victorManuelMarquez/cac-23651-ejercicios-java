package ar.com.codoacodo.personas.controller;

import ar.com.codoacodo.personas.bundle.PersonDto;
import ar.com.codoacodo.personas.mapper.PersonMapper;
import ar.com.codoacodo.personas.service.PersonRestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Data
@RestController
@RequestMapping(value = "/persons")
@SuppressWarnings("unused")
public class PersonController {

    private PersonRestService service;

    @GetMapping
    public ResponseEntity<?> index() {
        List<PersonDto> list = service.listPersons().stream().map(PersonMapper::toDto).toList();
        return new ResponseEntity<>(list.isEmpty() ? "Empty data bank." : list, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerPerson(@RequestBody PersonDto dto) {
        return new ResponseEntity<>(service.addPerson(PersonMapper.toEntity(dto)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDto> person(@PathVariable("id") String id) {
        return new ResponseEntity<>(PersonMapper.toDto(service.findPersonById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/filter-by-ages")
    public ResponseEntity<?> personsByAge(@RequestParam Integer since, @RequestParam Integer to) {
        List<PersonDto> list = service.filterPersonByAge(since, to).stream().map(PersonMapper::toDto).toList();
        return new ResponseEntity<>(list.isEmpty() ? "No results available." : list, HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-name")
    public ResponseEntity<PersonDto> personByName(@RequestParam String name) {
        return new ResponseEntity<>(PersonMapper.toDto(service.findPersonByName(name)), HttpStatus.OK);
    }

}
