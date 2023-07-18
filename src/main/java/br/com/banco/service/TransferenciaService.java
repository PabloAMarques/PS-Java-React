package br.com.banco.service;

import br.com.banco.model.Transferencia;

import java.time.LocalDate;
import java.util.List;

public interface TransferenciaService {

    Transferencia obterTransferenciaPorId(Long id);

    List<Transferencia> obterTodasTransferencias();

    Transferencia criarTransferencia(Transferencia transferencia);

    Transferencia atualizarTransferencia(Long id, Transferencia transferencia);

    void deletarTransferencia(Long id);

    List<Transferencia> consultarExtrato(LocalDate dataInicio, LocalDate dataFim, String nomeOperador);
}
