package cl.franco.auth.repository;

import cl.franco.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsuarioNombre(String usuarioNombre);
}
