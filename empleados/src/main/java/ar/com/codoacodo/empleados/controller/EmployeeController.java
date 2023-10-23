package ar.com.codoacodo.empleados.controller;

import ar.com.codoacodo.empleados.dto.EmployeeDto;
import ar.com.codoacodo.empleados.mapper.EmployeeMapper;
import ar.com.codoacodo.empleados.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Data
@RestController
@RequestMapping(value = "/employee")
@SuppressWarnings("unused")
public class EmployeeController {

    @SuppressWarnings("FieldMayBeFinal")
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<String> index() {
        return new ResponseEntity<>(service.employeesInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<EmployeeDto>> listAll() {
        return new ResponseEntity<>(service.listEmployees().stream().map(EmployeeMapper::toDto).toList(), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        service.addNewEmployee(EmployeeMapper.toEntity(employeeDto));
        return new ResponseEntity<>("Empleado agregado.", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{dni}")
    public ResponseEntity<EmployeeDto> employeeById(@PathVariable String dni) {
        return new ResponseEntity<>(EmployeeMapper.toDto(service.findEmployeeByDni(dni)), HttpStatus.OK);
    }

    @PostMapping(value = "/edit/{dni}")
    public ResponseEntity<EmployeeDto> editEmployee(@PathVariable String dni, @Valid @RequestBody EmployeeDto employeeDto) {
        employeeDto.setId(service.findEmployeeByDni(dni).getId());
        return new ResponseEntity<>(EmployeeMapper.toDto(service.modifyEmployee(EmployeeMapper.toEntity(employeeDto))), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{dni}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable String dni) {
        return new ResponseEntity<>(EmployeeMapper.toDto(service.fireEmployee(service.findEmployeeByDni(dni))), HttpStatus.OK);
    }

}
