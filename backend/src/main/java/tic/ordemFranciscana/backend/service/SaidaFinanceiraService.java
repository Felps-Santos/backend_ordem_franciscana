package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.SaidaFinanceira;
import tic.ordemFranciscana.backend.repository.SaidaFinanceiraRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SaidaFinanceiraService {

    private final SaidaFinanceiraRepository repository;

    public SaidaFinanceiraService(SaidaFinanceiraRepository repository) {
        this.repository = repository;
    }

    public List<SaidaFinanceira> listar() {
        return repository.findAll();
    }

    public Optional<SaidaFinanceira> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public SaidaFinanceira salvar(SaidaFinanceira saida) {
        saida.setId(null);
        return repository.save(saida);
    }

    @Transactional
    public boolean remover(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Transactional
    public SaidaFinanceira atualizar(Long id, SaidaFinanceira nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
