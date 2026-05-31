package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import tic.ordemFranciscana.backend.model.Grupo;
import tic.ordemFranciscana.backend.repository.GrupoRepository;

import java.util.List;
import java.util.Optional;

@Service
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

    public Grupo salvar(Grupo grupo) {
        grupo.setId(null);
        return repository.save(grupo);
    }

    public boolean remover(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    public Grupo atualizar(Long id, Grupo novo) {
        Optional<Grupo> grupoEncontrado = repository.findById(id);

        if (grupoEncontrado.isPresent()) {
            Grupo grupo = grupoEncontrado.get();

            grupo.setNome(novo.getNome());
            grupo.setStatus(novo.getStatus());

            return repository.save(grupo);
        }

        return null;
    }
}