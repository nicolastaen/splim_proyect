package com.duoc.splim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.splim.model.Juego;


@Repository
public interface JuegosRepository extends JpaRepository<Juego, String> {
}