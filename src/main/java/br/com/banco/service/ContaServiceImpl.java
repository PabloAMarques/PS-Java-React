package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import org.springframework.stereotype.Service;
import br.com.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;

    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta obterContaPorId(Long id) {
        return contaRepository.findById(id).orElse(null);
    }
}
