package cl.franco.auth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String usuarioNombre;

    @Column(nullable = false)
    private String clave;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roll_usuarios", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "roll")
    private Set<String> roles;

}
