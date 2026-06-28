package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Solicitacao;
import tic.ordemFranciscana.backend.repository.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SolicitacaoService {

    private final SolicitacaoRepository repository;

    public SolicitacaoService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public List<Solicitacao> listar() {
        return repository.findAll();
    }

    public Optional<Solicitacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Solicitacao salvar(Solicitacao solicitacao) {
        solicitacao.setId(null);
        return repository.save(solicitacao);
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
    public Solicitacao atualizar(Long id, Solicitacao nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
