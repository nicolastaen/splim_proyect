package com.duoc.splim.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_aporte;

    @NotNull
    private String juego_aportado;
    
    @NotNull
    private String usuario_apotador;
    
    @NotNull
    private int dinero_aportado;
    
    @NotBlank
    private String reseña_aporte;
    
    @NotNull
    private Date fecha_aporte;
}
