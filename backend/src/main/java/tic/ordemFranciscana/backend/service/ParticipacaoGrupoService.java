package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.ParticipacaoGrupo;
import tic.ordemFranciscana.backend.repository.ParticipacaoGrupoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ParticipacaoGrupoService {

    private final ParticipacaoGrupoRepository repository;

    public ParticipacaoGrupoService(ParticipacaoGrupoRepository repository) {
        this.repository = repository;
    }

    public List<ParticipacaoGrupo> listar() {
        return repository.findAll();
    }

    public Optional<ParticipacaoGrupo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ParticipacaoGrupo salvar(ParticipacaoGrupo participacao) {
        participacao.setId(null);
        return repository.save(participacao);
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
    public ParticipacaoGrupo atualizar(Long id, ParticipacaoGrupo nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
