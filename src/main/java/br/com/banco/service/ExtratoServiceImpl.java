package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExtratoServiceImpl implements ExtratoService {

    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public ExtratoServiceImpl(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Override
    public List<Transferencia> consultarExtrato(LocalDate dataInicio, LocalDate dataFim, String nomeOperador) {
        LocalDateTime dataInicioDateTime = dataInicio != null ? LocalDateTime.of(dataInicio, LocalTime.MIN) : null;
        LocalDateTime dataFimDateTime = dataFim != null ? LocalDateTime.of(dataFim, LocalTime.MAX) : null;

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
}
