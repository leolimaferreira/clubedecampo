package com.clubedecampo.service;

import com.clubedecampo.entity.Area;
import com.clubedecampo.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public void salvar(Area area) {
        areaRepository.save(area);
    }

    public Optional<Area> buscarPorId(UUID id) {
        return areaRepository.findById(id);
    }
}
