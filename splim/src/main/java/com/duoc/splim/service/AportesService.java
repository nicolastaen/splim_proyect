package com.duoc.splim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.splim.repository.AporteRepository;


@Service
public class AportesService {

    @Autowired
    private AporteRepository aporteRepository;


}
