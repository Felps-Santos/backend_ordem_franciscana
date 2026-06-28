package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Funcao;
import tic.ordemFranciscana.backend.repository.FuncaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FuncaoService {

    private final FuncaoRepository repository;

    public FuncaoService(FuncaoRepository repository) {
        this.repository = repository;
    }

    public List<Funcao> listar() {
        return repository.findAll();
    }

    public Optional<Funcao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Funcao salvar(Funcao funcao) {
        funcao.setId(null);
        return repository.save(funcao);
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
    public Funcao atualizar(Long id, Funcao nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
