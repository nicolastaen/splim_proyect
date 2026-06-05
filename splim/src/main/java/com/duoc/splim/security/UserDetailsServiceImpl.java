package com.duoc.splim.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.duoc.splim.model.UserCredentials;
import com.duoc.splim.repository.UserCredentialsRepository;

import java.util.List;
/**
 * Implementacio de UserDetailsService requerida por Spring Security
 * 
 * Sprig la llama internamente durante el proceso de autentificacion
 * (AuthenticationManager.authenticate) para cargar al usuario desde la BD
 * y comparar su contrasela encriptada con la recibida en el login
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials usuario = userCredentialsRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + "no encontrado"));

                return new User(
                    usuario.getUsername(),
                    usuario.getPassword(),
                    List.of(new SimpleGrantedAuthority(usuario.getRole()))
                );
    }

}
