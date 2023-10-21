package ar.com.codoacodo.empleados.service;

import ar.com.codoacodo.empleados.entity.Employee;
import ar.com.codoacodo.empleados.exception.EmployeeNotFoundException;
import ar.com.codoacodo.empleados.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {

    @SuppressWarnings("FieldMayBeFinal")
    private EmployeeRepository repository;

    public void addNewEmployee(Employee employee) {
        repository.add(employee);
    }

    public Employee fireEmployee(Employee employee) {
        return repository.remove(employee).orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee modifyEmployee(Employee employee) {
        return repository.update(employee).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> listEmployees() {
        return repository.listAll();
    }

    public Employee findEmployeeByDni(String dni) {
        return repository.listAll().stream().filter(e -> e.getDni().equals(dni)).findFirst().orElseThrow(EmployeeNotFoundException::new);
    }

    public String employeesInfo() {
        int size = repository.listAll().size();
        return size == 0 ? "No hay empleados." : "Hay " + size + " empleados.";
    }

}
