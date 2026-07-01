package com.duoc.splim.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.splim.dto.UsuarioAutorDto;
import com.duoc.splim.model.Juego;
import com.duoc.splim.model.Usuario;
import com.duoc.splim.repository.JuegosRepository;
import com.duoc.splim.repository.UsuarioRepository;

@Service
public class JuegoService {

    @Autowired
    private JuegosRepository juegosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger log = LoggerFactory.getLogger(JuegoService.class);

    public List<Juego> getJuegos() {
        log.info("mostrando lista de juegos");
        return juegosRepository.findAll();
    }

    public Juego saveJuego(Juego juego) {
        log.info("guardando juego...");
        
                // 👇 Verifica que el autor exista en la BD
        if (juego.getAutor() != null && juego.getAutor().getId_usuario() != null) {
            Usuario autor = usuarioRepository
                .findById(juego.getAutor().getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            juego.setAutor(autor);
        }
        return juegosRepository.save(juego);
    }

    public Juego getjuegoId(String id) {
        log.info("mostrando juego por id");
        return juegosRepository.findById(id).orElse(null);
    }

    public Juego updateJuego(Juego juego) {
        log.info("actualizando datos del juego");
        if (!juegosRepository.existsById(juego.getId_juego())) {
            return null;
        }
        return juegosRepository.save(juego);
    }

    public void deleteJuego(String id) {
        log.info("borrando juego por id");
        juegosRepository.deleteById(id);
    }

    public List<UsuarioAutorDto> getJuegosAutorSimple() {
        log.info("mostrando juegos simplificado");

        return juegosRepository.findAll().stream()
                .map(l -> new UsuarioAutorDto(
                        l.getTitulo(),
                        l.getAutor().getNombre_usuario(),
                        l.getAutor().getFoto_perfil()
                ))
                .toList();
    }
}

