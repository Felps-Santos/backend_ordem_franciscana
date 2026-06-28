package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Documento;
import tic.ordemFranciscana.backend.repository.DocumentoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DocumentoService {

    private final DocumentoRepository repository;

    public DocumentoService(DocumentoRepository repository) {
        this.repository = repository;
    }

    public List<Documento> listar() {
        return repository.findAll();
    }

    public Optional<Documento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Documento salvar(Documento documento) {
        documento.setId(null);
        return repository.save(documento);
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
    public Documento atualizar(Long id, Documento novo) {
        if (!repository.existsById(id)) {
            return null;
        }
        novo.setId(id);
        return repository.save(novo);
    }
}
