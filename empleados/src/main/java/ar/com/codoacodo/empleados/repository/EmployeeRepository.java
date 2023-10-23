package ar.com.codoacodo.empleados.repository;

import ar.com.codoacodo.empleados.entity.Employee;
import ar.com.codoacodo.empleados.exception.DuplicatedEmployeeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class EmployeeRepository implements BasicRepository<Employee> {

    @SuppressWarnings("FieldMayBeFinal")
    private List<Employee> employees;

    @Override
    public void add(Employee data) {
        employees.forEach(e -> System.out.println(data + " === " + e.equals(data)));
        if (employees.stream().anyMatch(e -> e.equals(data)))
            throw new DuplicatedEmployeeException();
        data.setId(employees.isEmpty() ? 0 : employees.size() - 1);
        employees.add(data);
    }

    @Override
    public Optional<Employee> remove(Employee data) {
        return Optional.ofNullable(employees.remove(data) ? data : null);
    }

    @Override
    public Optional<Employee> update(Employee data) {
        Optional<Employee> optional = employees.stream().filter(e -> e.getId().equals(data.getId())).findFirst();
        if (optional.isPresent()) {
            Employee employee = optional.get();
            employees.remove(employee);
            employee.setDni(data.getDni());
            employee.setNombre(data.getNombre());
            employee.setApellido(data.getApellido());
            employee.setFechaNac(data.getFechaNac());
            employees.add(employee.getId(), employee);
        }
        return optional;
    }

    @Override
    public List<Employee> listAll() {
        return employees;
    }

}
