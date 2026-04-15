package com.duoc.splim.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_juego;
    
    @NotNull
    private Usuario autor;

    @NotBlank
    private String titulo;
    
    @NotBlank
    private String portad;
    
    @NotNull
    private Date fecha_lanzamiento;


}
