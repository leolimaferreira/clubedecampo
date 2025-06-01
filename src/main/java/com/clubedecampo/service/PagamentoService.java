package com.clubedecampo.service;

import com.clubedecampo.entity.Pagamento;
import com.clubedecampo.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public void salvar(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }
}
