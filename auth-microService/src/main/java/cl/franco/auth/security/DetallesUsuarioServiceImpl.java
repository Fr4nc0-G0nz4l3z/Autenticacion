package cl.franco.auth.security;

import cl.franco.auth.model.Usuario;
import cl.franco.auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetallesUsuarioServiceImpl implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usuarioNombre) throws UserNameNotFoundException{
        Usuario usuario = usuarioRepository.findByUsername(usuarioNombre)
            .orElseThrow(() -> new UserNameNotFoundException("Usuarion no encontrado: " + usuarioNombre));
    
        return org.springframework.security.core.userdetails.User.builder()
            .usuarioNombre(usuario.getUsuarioNombre())
            .clave(usuario.getClave())
            .authorities(usuario.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()))
            .build();
    
    }
}
