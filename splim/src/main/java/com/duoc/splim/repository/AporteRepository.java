package com.duoc.splim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.splim.model.Aportes;


@Repository
public interface AporteRepository extends JpaRepository<Aportes, String> {
}