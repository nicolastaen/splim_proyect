package com.duoc.splim.controller;

import com.duoc.splim.model.Aportes;
import com.duoc.splim.service.AportesService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aportes")
public class AportesController {

    @Autowired
    private AportesService aportesService;

    @GetMapping
    public ResponseEntity<List<Aportes>> listarAportes() {
        return ResponseEntity.ok(aportesService.getAportes());
    }

    @PostMapping
    public ResponseEntity<Aportes> agregarAporte(@Valid @RequestBody Aportes aportes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aportesService.saveAporte(aportes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aportes> buscarAporte(@PathVariable String id) {
        Aportes aportes = aportesService.getAporteId(id);
        if (aportes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aportes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aportes> actualizarAporte(@PathVariable String id, @Valid @RequestBody Aportes aportes) {
        aportes.setId_aporte(id);
        Aportes actualizado = aportesService.updateAporte(aportes);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAporte(@PathVariable String id) {
        aportesService.deleteAporte(id);
        return ResponseEntity.noContent().build();
    }
}
