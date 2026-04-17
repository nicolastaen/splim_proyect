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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_usuario;
    
    @NotNull
    private Date fecha_nacimiento;
    
    @NotNull
    private Date fecha_creacion_cuenta;
    
    @NotBlank
    private String nombre_usuario;
    
    @NotBlank
    private String foto_perfil;
    
    @NotBlank
    private String descripcion_perfil;


}
