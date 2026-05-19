package cl.franco.auth.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class RegistroRequest {

    @NotBlank
    private String usuarioNombre;

    @NotBlank
    private String clave;
    private Set<String> roles;
}
