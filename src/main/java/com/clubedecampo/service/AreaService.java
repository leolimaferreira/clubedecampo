package com.clubedecampo.service;

import com.clubedecampo.entity.Area;
import com.clubedecampo.exception.OperacaoNaoPermitidaException;
import com.clubedecampo.repository.AreaRepository;
import com.clubedecampo.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final ReservaRepository reservaRepository;

    public Area salvar(Area area) {
        return areaRepository.save(area);
    }

    public void deletar(Area area) {
        if (possuiReservas(area)) {
            throw new OperacaoNaoPermitidaException("Area possui reservas");
        }

        areaRepository.delete(area);
    }

    public Optional<Area> buscarPorId(UUID id) {
        return areaRepository.findById(id);
    }

    public boolean possuiReservas(Area area) {
        return reservaRepository.existsByArea(area);
    }

    public void atualizar(Area area) {
        areaRepository.save(area);
    }

    public List<Area> listar() {
        return areaRepository.findAll();
    }
}
