package com.duoc.splim.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aportes")
public class Aportes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_aporte;

    @NotNull
    private Juego juego_aportado;
    
    @NotNull
    private Usuario usuario_apotador;
    
    @NotNull
    private int dinero_aportado;
    
    @NotBlank
    private String reseña_aporte;
    
    @NotNull
    private Date fecha_aporte;
}
