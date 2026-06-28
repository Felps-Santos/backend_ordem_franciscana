package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Membro;
import tic.ordemFranciscana.backend.repository.MembroRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MembroService {

    private final MembroRepository repository;

    public MembroService(MembroRepository repository) {
        this.repository = repository;
    }

    public List<Membro> listar() {
        return repository.findAll();
    }

    public Optional<Membro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Membro salvar(Membro membro) {
        membro.setId(null);
        return repository.save(membro);
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
    public Membro atualizar(Long id, Membro novo) {
        if (!repository.existsById(id)) {
            return null;
        }
        novo.setId(id);
        return repository.save(novo);
    }
}
