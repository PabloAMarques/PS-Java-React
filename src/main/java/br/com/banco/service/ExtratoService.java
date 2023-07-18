package br.com.banco.service;

import br.com.banco.model.Transferencia;

import java.time.LocalDate;
import java.util.List;

public interface ExtratoService {

    List<Transferencia> consultarExtrato(LocalDate dataInicio, LocalDate dataFim, String nomeOperador);
}
