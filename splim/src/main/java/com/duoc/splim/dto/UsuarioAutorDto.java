package com.duoc.splim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAutorDto {

    private String nombre_juego;
    private String nombre_usuario;
    private String foto_perfil;
}
