package cl.franco.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticoRequest {
    @NotBlank(message = "El usuario es obligatorio.")
    private String usuarioNombre;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String clave;
}
