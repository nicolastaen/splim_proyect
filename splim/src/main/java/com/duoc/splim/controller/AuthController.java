package com.duoc.splim.controller;

import com.duoc.splim.dto.AuthRequest;
import com.duoc.splim.repository.UserCredentialsRepository;
import com.duoc.splim.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.duoc.splim.model.UserCredentials;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.duoc.splim.dto.AuthResponse;




/**
 * Controlador de autentifiacion
 * 
 * endpoints publicos (no requiere JWT):
 *  POST /api/v1/auth/register  → registra un nuevo usuario con rol ROLE_USER
 *  POST /api/v1/auth/login     → autentificar credenciales y devuelve un jwt
*/
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * registra un nuevo usuario con eol USER
     * La contraseña se almacea encriptada con BCrypt
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (userCredentialsRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername(request.getUsername());
        userCredentials.setPassword(passwordEncoder.encode(request.getPassword()));
        userCredentials.setRole("ROLE_USER");
        userCredentialsRepository.save(userCredentials);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("usuario registrado con exito");
    }
    
     /**
     * Autentica al usuario y devuelve un JWT válido por 24 horas.
     * El token debe enviarse en el header Authorization de los siguientes requests:
     *   Authorization: Bearer <token>
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request ) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String role = auth.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");
            
            String token = jwtUtil.generateToken(request.getUsername(), role);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }        
    }
}
