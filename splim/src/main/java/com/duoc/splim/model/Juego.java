package com.duoc.splim.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_juego;
    
    @NotBlank
    private String titulo;
    
    @NotBlank
    private String portada;
    
    @NotNull
    private Date fecha_lanzamiento;
    
// LLAVER FORANEAS

    @OneToMany
    private List<Aportes> aportes;

    //@NotNull
    //@ManyToMany (cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "id_usuario" = nullable = false)
    //private Usuario autor;
    
    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id_usuario")
    private Usuario autor;
}
