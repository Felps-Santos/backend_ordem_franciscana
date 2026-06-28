package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.EntradaFinanceira;
import tic.ordemFranciscana.backend.repository.EntradaFinanceiraRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EntradaFinanceiraService {

    private final EntradaFinanceiraRepository repository;

    public EntradaFinanceiraService(EntradaFinanceiraRepository repository) {
        this.repository = repository;
    }

    public List<EntradaFinanceira> listar() {
        return repository.findAll();
    }

    public Optional<EntradaFinanceira> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public EntradaFinanceira salvar(EntradaFinanceira entrada) {
        entrada.setId(null);
        return repository.save(entrada);
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
    public EntradaFinanceira atualizar(Long id, EntradaFinanceira nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
