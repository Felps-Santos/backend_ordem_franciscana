package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.HistoricoFormacao;
import tic.ordemFranciscana.backend.repository.HistoricoFormacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HistoricoFormacaoService {

    private final HistoricoFormacaoRepository repository;

    public HistoricoFormacaoService(HistoricoFormacaoRepository repository) {
        this.repository = repository;
    }

    public List<HistoricoFormacao> listar() {
        return repository.findAll();
    }

    public Optional<HistoricoFormacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public HistoricoFormacao salvar(HistoricoFormacao historico) {
        historico.setId(null);
        return repository.save(historico);
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
    public HistoricoFormacao atualizar(Long id, HistoricoFormacao novo) {
        if (!repository.existsById(id)) {
            return null;
        }
        novo.setId(id);
        return repository.save(novo);
    }
}
