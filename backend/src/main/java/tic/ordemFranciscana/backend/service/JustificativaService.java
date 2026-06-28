package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Justificativa;
import tic.ordemFranciscana.backend.repository.JustificativaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class JustificativaService {

    private final JustificativaRepository repository;

    public JustificativaService(JustificativaRepository repository) {
        this.repository = repository;
    }

    public List<Justificativa> listar() {
        return repository.findAll();
    }

    public Optional<Justificativa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Justificativa salvar(Justificativa justificativa) {
        justificativa.setId(null);
        return repository.save(justificativa);
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
    public Justificativa atualizar(Long id, Justificativa nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
