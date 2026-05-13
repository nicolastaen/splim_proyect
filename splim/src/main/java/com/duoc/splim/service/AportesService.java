package com.duoc.splim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.splim.model.Aportes;
import com.duoc.splim.repository.AporteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class AportesService {
    
    @Autowired
    private AporteRepository aporteRepository;

    private static final Logger log = LoggerFactory.getLogger(AportesService.class);

    public List<Aportes> getAportes() {
        log.info("listando aportes");
        return aporteRepository.findAll();
    }

    public Aportes saveAporte(Aportes aporte) {
        log.info("guardando aporte");
        return aporteRepository.save(aporte);
    }

    public Aportes getAporteId(String id) {
        log.info("buscando aportes por id");
        return aporteRepository.findById(id).orElse(null);
    }

    public Aportes updateAporte(Aportes aporte) {
        log.info("actualizando datos de aportes");
        if (!aporteRepository.existsById(aporte.getId_aporte())) {
            return null;
        }
        return aporteRepository.save(aporte);
    }

    public void deleteAporte(String id) {
        log.info("borrando aporte por id");
        aporteRepository.deleteById(id);
    }
}
