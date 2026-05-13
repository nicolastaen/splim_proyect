package com.duoc.splim.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.splim.model.Usuario;
import com.duoc.splim.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    public List<Usuario> getUsuarios() {
        log.info("listando ususarios");
        return usuarioRepository.findAll();
    }

    public Usuario saveUsuario(Usuario usuario) {
        log.info("guardando usuario");
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioId(String id) {
        log.info("buscando ususario por id");
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario updateUsuario(Usuario usuario) {
        log.info("actualizando datos de usuario");
        if (!usuarioRepository.existsById(usuario.getId_usuario())) {
            return null;
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuaio(String id) {
        log.info("borrando usuario por id");
        usuarioRepository.deleteById(id);
    }

}
