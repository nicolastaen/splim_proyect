package com.duoc.splim.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMock;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.duoc.splim.controller.JuegoController;
import com.duoc.splim.model.Juego;
import com.duoc.splim.model.Usuario;
import com.duoc.splim.service.JuegoService;

@ExtendWith(MockitoExtension.class)
public class JuegosControllerTest {

    @Mock
    private JuegoService juegoService;

    @InjectMocks
    private JuegoController juegoController;

    @Test
    void crearJuego_retorna201_cuandoExisteUsuario(){
        
        Usuario usuario = new Usuario();
        
        Juego juego = new Juego();
    }
}
