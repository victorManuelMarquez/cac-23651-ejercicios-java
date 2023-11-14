package ar.com.codoacodo.personasv4.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoredPersonDto {

    @NotBlank
    @Size(max = 40, message = "El nombre es muy largo.")
    private String name;
    @NotBlank
    @Size(max = 40, message = "El apellido es muy largo.")
    private String lastName;
    @NotBlank
    private String birthDate;

}
