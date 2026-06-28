package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Aprovacao;
import tic.ordemFranciscana.backend.repository.AprovacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AprovacaoService {

    private final AprovacaoRepository repository;

    public AprovacaoService(AprovacaoRepository repository) {
        this.repository = repository;
    }

    public List<Aprovacao> listar() {
        return repository.findAll();
    }

    public Optional<Aprovacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Aprovacao salvar(Aprovacao aprovacao) {
        aprovacao.setId(null);
        return repository.save(aprovacao);
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
    public Aprovacao atualizar(Long id, Aprovacao nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
