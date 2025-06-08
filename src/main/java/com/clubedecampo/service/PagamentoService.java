package com.clubedecampo.service;

import com.clubedecampo.entity.Pagamento;
import com.clubedecampo.exception.OperacaoNaoPermitidaException;
import com.clubedecampo.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Optional<Pagamento> buscarPorId(UUID id) {
        return pagamentoRepository.findById(id);
    }

    public void deletar(Pagamento pagamento) {
        /*if(pagamento.getStatus().equalsIgnoreCase("pago")) {
            throw new OperacaoNaoPermitidaException("Só é possível deletar pagamentos pendentes");
        }*/

        pagamentoRepository.delete(pagamento);
    }

    public void atualizar(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listar() {
        return pagamentoRepository.findAll();
    }

    public void calcularValorCorrigido(Pagamento pagamento) {
        LocalDate dataPagamento = pagamento.getDataPagamento();
        LocalDate dataVencimento = pagamento.getMensalidade().getDataVencimento();
        BigDecimal valorBase = pagamento.getMensalidade().getValorBase();

        if (dataPagamento.isAfter(dataVencimento)) {
            BigDecimal multa = new BigDecimal("0.05");
            BigDecimal valorCorrigido = valorBase.multiply(BigDecimal.ONE.add(multa))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

            pagamento.getMensalidade().setValorCorrigido(valorCorrigido);
        } else {
            pagamento.getMensalidade().setValorCorrigido(valorBase);
        }
    }
}
