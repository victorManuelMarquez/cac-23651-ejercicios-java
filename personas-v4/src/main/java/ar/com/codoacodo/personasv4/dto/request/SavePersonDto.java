package ar.com.codoacodo.personasv4.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SavePersonDto {

    @PositiveOrZero
    private Long id;
    @NotBlank
    @Size(max = 9, message = "El dni es muy largo.")
    private String personalId;
    @NotBlank
    @Size(max = 40, message = "El nombre es muy largo.")
    private String name;
    @NotBlank
    @Size(max = 40, message = "El apellido es muy largo.")
    private String lastName;
    @NotBlank
    private String birthDate;

}
