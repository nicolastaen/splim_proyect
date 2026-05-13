package com.duoc.splim.controller;

import com.duoc.splim.dto.UsuarioAutorDto;
import com.duoc.splim.model.Juego;
import com.duoc.splim.service.JuegoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/juego")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public ResponseEntity<List<Juego>> listarJuegos() {
        return ResponseEntity.ok(juegoService.getJuegos());
    }

    @PostMapping
    public ResponseEntity<Juego> agregarJuego(@Valid @RequestBody Juego juego) {
        return ResponseEntity.status(HttpStatus.CREATED).body(juegoService.saveJuego(juego));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> buscarJuego(@PathVariable String id) {
        Juego juego = juegoService.getjuegoId(id);
        if (juego == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(juego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Juego> actualizarJuego(@PathVariable String id, @Valid @RequestBody Juego juego) {
        juego.setId_juego(id);
        Juego actualizado = juegoService.updateJuego(juego);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable String id) {
        juegoService.deleteJuego(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/montrarsimple")
    public ResponseEntity<List<UsuarioAutorDto>> getJuegosAutorSimple() {
        System.out.println("[LibroController] -> getJuegosAutorSimple");
        return ResponseEntity.ok(juegoService.getJuegosAutorSimple());
    }

}
