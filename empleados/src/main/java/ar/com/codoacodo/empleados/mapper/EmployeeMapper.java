package ar.com.codoacodo.empleados.mapper;

import ar.com.codoacodo.empleados.dto.EmployeeDto;
import ar.com.codoacodo.empleados.entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeDto dto) {
        return new Employee(
                dto.getId(),
                dto.getDni(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getFechaNac()
        );
    }

    public static EmployeeDto toDto(Employee entity) {
        return new EmployeeDto(
                entity.getId(),
                entity.getDni(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getFechaNac()
        );
    }

}
