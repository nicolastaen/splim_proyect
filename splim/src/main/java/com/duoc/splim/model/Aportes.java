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
@Table(name = "aportes")
public class Aportes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_aporte;
    @NotBlank
    private Juego juego_aportado;

    private Usuario usuario_apotador;

    private int dinero_aportado;
    @NotBlank
    private String reseña_aporte;
}
