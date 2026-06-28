package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Presenca;
import tic.ordemFranciscana.backend.repository.PresencaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PresencaService {

    private final PresencaRepository repository;

    public PresencaService(PresencaRepository repository) {
        this.repository = repository;
    }

    public List<Presenca> listar() {
        return repository.findAll();
    }

    public Optional<Presenca> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Presenca salvar(Presenca presenca) {
        presenca.setId(null);
        return repository.save(presenca);
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
    public Presenca atualizar(Long id, Presenca nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
