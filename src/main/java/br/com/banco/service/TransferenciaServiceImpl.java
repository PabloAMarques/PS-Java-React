package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaServiceImpl(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Override
    public Transferencia obterTransferenciaPorId(Long id) {
        return transferenciaRepository.findById(id).orElse(null);
    }

    @Override
    public void deletarTransferencia(Long id) {
        transferenciaRepository.deleteById(id);
    }

    @Override
    public List<Transferencia> consultarExtrato(LocalDate dataInicio, LocalDate dataFim, String nomeOperador) {
        LocalDateTime dataInicioDateTime = dataInicio != null ? dataInicio.atStartOfDay() : null;
        LocalDateTime dataFimDateTime = dataFim != null ? dataFim.atStartOfDay().plusDays(1) : null;

        if (dataInicio == null && dataFim == null && nomeOperador == null) {
            return transferenciaRepository.findAll();
        } else if (dataInicio == null && dataFim == null) {
            return transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
        } else if (dataInicio == null) {
            return transferenciaRepository.findByDataTransferenciaBeforeAndNomeOperadorTransacao(dataFimDateTime, nomeOperador);
        } else if (dataFim == null) {
            return transferenciaRepository.findByDataTransferenciaAfterAndNomeOperadorTransacao(dataInicioDateTime, nomeOperador);
        } else {
            return transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(dataInicioDateTime, dataFimDateTime, nomeOperador);
        }
    }

    @Override
    public Transferencia atualizarTransferencia(Long id, Transferencia transferencia) {
        Transferencia transferenciaExistente = transferenciaRepository.findById(id).orElse(null);
        if (transferenciaExistente != null) {
            transferenciaExistente.setDataTransferencia(transferencia.getDataTransferencia());
            transferenciaExistente.setValor(transferencia.getValor());
            transferenciaExistente.setTipo(transferencia.getTipo());
            transferenciaExistente.setNomeOperadorTransacao(transferencia.getNomeOperadorTransacao());
            transferenciaExistente.setConta(transferencia.getConta());
            return transferenciaRepository.save(transferenciaExistente);
        }
        return null;
    }

    @Override
    public Transferencia criarTransferencia(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }
    
    @Override
    public List<Transferencia> obterTodasTransferencias() {
        return transferenciaRepository.findAll();
    }
    
}
