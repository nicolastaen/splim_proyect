package com.duoc.splim;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

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
        
        // primero verificaremos que el metodo agregar un juego funcione correctamente
        //para eso crearemos un juego con un usuario valido y probaremos como actua


        Date fecha_N = new Date(06-06-2007);
        
        Date fecha_C = new Date(06-06-2026);

        Usuario usuario = new Usuario("a1",
                                    fecha_N,
                                    fecha_C,
                                    "gomor",
                                    "xxxx",
                                    "TEST",
                                    null);
        
        Date fecha_L = new Date(06-06-2026);

        Juego juego = new Juego("A1",
                                "TEST GAME",
                                "xxxx",
                                fecha_L,
                                null,
                                usuario);

        /**  
         * para simular el comportamiento del servicio usamos (mock)
         * Asi evitamos acceder a la base de datos en una prueba unitaria
         * cuando el servicio intente guaradar el juego le decimos que devuelca el mismo juego (como si lu hubiese guardado)
         * cuando el controlador invoque saveLibro con ese juego, Mockito devolvera ese mismo juego al instante
         * sin ejecutar logica real, sin repositorio y sin DB
        */
       when(juegoService.saveJuego(juego)).thenReturn(juego);

       /**
        * llamamos el metodo del controlador que queremos probar.
        * el resultado es un ResponseEntity<Juego> con estado HTTP y cuerpo
        */
       var respuesta = juegoController.agregarJuego(juego);

       //para que el test sea completo, verificamos varias respuestas

       //1 la respuesta no tiene que ser nula
       assertNotNull(respuesta);

       //2 el estado http esperado al crear un recurso es 201(CREATED)
       assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

       //3 el cuerpo de la respuesta debe existir
       var body = respuesta.getBody();
       assertNotNull(body);

       //4 validamos un dato clave del cuerpo para confirmar que se devolvio el juego correcto
       assertEquals("TEST GAME", body.getTitulo());
    }
}
