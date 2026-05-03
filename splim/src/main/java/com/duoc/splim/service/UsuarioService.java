package com.duoc.splim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.splim.model.Usuario;
import com.duoc.splim.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

        public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioId(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario updateUsuario(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId_usuario())) {
            return null;
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuaio(String id) {
        usuarioRepository.deleteById(id);
    }

}
