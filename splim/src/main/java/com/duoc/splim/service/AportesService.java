package com.duoc.splim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.splim.model.Aportes;
import com.duoc.splim.repository.AporteRepository;



@Service
public class AportesService {
    
    @Autowired
    private AporteRepository aporteRepository;

    public List<Aportes> getAportes() {
        return aporteRepository.findAll();
    }

    public Aportes saveAporte(Aportes aporte) {
        return aporteRepository.save(aporte);
    }

    public Aportes getAporteId(String id) {
        return aporteRepository.findById(id).orElse(null);
    }

    public Aportes updateAporte(Aportes aporte) {
        if (!aporteRepository.existsById(aporte.getId_aporte())) {
            return null;
        }
        return aporteRepository.save(aporte);
    }

    public void deleteAporte(String id) {
        aporteRepository.deleteById(id);
    }
}
