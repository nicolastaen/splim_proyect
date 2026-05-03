package com.duoc.splim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.splim.model.Juego;
import com.duoc.splim.repository.JuegosRepository;

@Service
public class JuegoService {

    @Autowired
    private JuegosRepository juegosRepository;

    public List<Juego> getJuegos() {
        return juegosRepository.findAll();
    }

    public Juego saveJuego(Juego juego) {
        return juegosRepository.save(juego);
    }

    public Juego getjuegoId(String id) {
        return juegosRepository.findById(id).orElse(null);
    }

    public Juego updateJuego(Juego juego) {
        if (!juegosRepository.existsById(juego.getId_juego())) {
            return null;
        }
        return juegosRepository.save(juego);
    }

    public void deleteJuego(String id) {
        juegosRepository.deleteById(id);
    }

}

