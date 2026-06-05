package com.duoc.splim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.splim.dto.AuthRequest;
import com.duoc.splim.repository.UserCredentialsRepository;
import com.duoc.splim.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    public String postMethodName(@RequestBody AuthRequest request) {
        //TODO: process POST request
        
        return entity;
    }
    
}
