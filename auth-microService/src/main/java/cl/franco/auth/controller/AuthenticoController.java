package cl.franco.auth.controller;



import cl.franco.auth.dto.AuthenticoRequest;
import cl.franco.auth.dto.AuthenticoResponse;
import cl.franco.auth.dto.RegistroRequest;
import cl.franco.auth.security.JwtUtil;
import cl.franco.auth.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthenticoController {
    
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    private static final Logger log = LoggerFactory.getLogger(AuthenticoController.class);

    @PostMapping("/login")
    public ResponseEntity<AuthenticoResponse> login(@Valid @RequestBody AuthenticoRequest request){
        MDC.put("user", request.getUsuarioNombre());
        log.info("Intento de Logearse");

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsuarioNombre(), request.getClave()));
        UserDetails detallesUsuario = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(detallesUsuario);

        log.info("Logeo exitoso para el usuario: ", request.getUsuarioNombre());
        return ResponseEntity.ok(new AuthenticoResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistroRequest request){
        String usuarioNombre = request.getUsuarioNombre();
        MDC.put("user", usuarioNombre);
        log.info("Intento de registro para usuario: {}", usuarioNombre);

        try{
            usuarioService.registroUsuario(usuarioNombre, request.getClave(), request.getRoles());
            log.info("Usuario registrado exitosamente: {}", usuarioNombre);
            return ResponseEntity.status(HttpStatus.CREATED).body("El usuario se ha registrado exitosamente");
        } catch (RuntimeException e) {
            log.error("Fallo en registro para usuario: {} - {}", usuarioNombre, e.getMessage());
            throw e; // El manejador global devolverá 400 o 409 según corresponda
        } finally {
            MDC.clear();

        }
    }



}
