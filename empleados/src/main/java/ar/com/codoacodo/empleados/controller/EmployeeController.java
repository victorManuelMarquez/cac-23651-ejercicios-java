package ar.com.codoacodo.empleados.controller;

import ar.com.codoacodo.empleados.entity.Employee;
import ar.com.codoacodo.empleados.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Data
@RestController
@RequestMapping(value = "/employee")
@SuppressWarnings("unused")
public class EmployeeController {

    @SuppressWarnings("FieldMayBeFinal")
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(service.employeesInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(service.listEmployees(), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody Employee employee) {
        service.addNewEmployee(employee);
        return new ResponseEntity<>("Empleado agregado.", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{dni}")
    public ResponseEntity<?> employeeById(@PathVariable String dni) {
        return new ResponseEntity<>(service.findEmployeeByDni(dni), HttpStatus.OK);
    }

    @PostMapping(value = "/edit/{dni}")
    public ResponseEntity<?> editEmployee(@PathVariable String dni, @Valid @RequestBody Employee employee) {
        Employee old = service.findEmployeeByDni(dni);
        employee.setId(old.getId()); // preservo el identificador de la "bd"
        return new ResponseEntity<>(service.modifyEmployee(employee), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{dni}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String dni) {
        return new ResponseEntity<>(service.fireEmployee(service.findEmployeeByDni(dni)), HttpStatus.OK);
    }

}
