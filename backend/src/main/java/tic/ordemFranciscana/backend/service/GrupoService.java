package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Grupo;
import tic.ordemFranciscana.backend.repository.GrupoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GrupoService {

    private final GrupoRepository repository;

    public GrupoService(GrupoRepository repository) {
        this.repository = repository;
    }

    public List<Grupo> listar() {
        return repository.findAll();
    }

    public Optional<Grupo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        grupo.setId(null);
        return repository.save(grupo);
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
    public Grupo atualizar(Long id, Grupo novo) {
        if (!repository.existsById(id)) {
            return null;
        }
        novo.setId(id);
        return repository.save(novo);
    }
}
