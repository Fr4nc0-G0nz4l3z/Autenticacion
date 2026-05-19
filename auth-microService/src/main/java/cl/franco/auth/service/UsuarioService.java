package cl.franco.auth.service;

import cl.franco.auth.model.Usuario;
import cl.franco.auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void registroUsuario(String usuarioNombre, String rawPassword, Set<String> roles){
        if (usuarioRepository.findByUsuarioNombre(usuarioNombre).isPresent()){
            throw new RuntimeException("el usuario ya existe.");
        }
        Usuario usuario = new Usuario();
        usuario.setUsuarioNombre(usuarioNombre);
        usuario.setClave(passwordEncoder.encode(rawPassword));
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
    
    
    
    
    }







}
