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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_usuario;

    private Date fecha_nacimiento;

    private Date fecha_creacion_cuenta;

    private String nombre_usuario;

    private String foto_perfil;

    private String descripcion_perfil;


}
