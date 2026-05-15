package com.duoc.splim.controller;

import com.duoc.splim.dto.SteamApiDto;
import com.duoc.splim.service.SteamApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/steamprofile")
public class SteamProfileController {

    @Autowired
    private SteamApiService steamApiService;

    /**
     * devuelve informacion del perfil de steam del cual se ingrese el codigo de perfil
     * 
     * Ejemplos:
     *  GET /api/v1/steamprofile/76561198996847108
     */

    @GetMapping("/{id}")
    public ResponseEntity<SteamApiDto> steamdatos(@PathVariable String id){
        SteamApiDto resultado = steamApiService.obtenerDatosUsuario(id);
                if (resultado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado);
    }
}
