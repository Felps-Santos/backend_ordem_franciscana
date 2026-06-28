package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.EtapaFormacao;
import tic.ordemFranciscana.backend.repository.EtapaFormacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EtapaFormacaoService {

    private final EtapaFormacaoRepository repository;

    public EtapaFormacaoService(EtapaFormacaoRepository repository) {
        this.repository = repository;
    }

    public List<EtapaFormacao> listar() {
        return repository.findAll();
    }

    public Optional<EtapaFormacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public EtapaFormacao salvar(EtapaFormacao etapa) {
        etapa.setId(null);
        return repository.save(etapa);
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
    public EtapaFormacao atualizar(Long id, EtapaFormacao nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
