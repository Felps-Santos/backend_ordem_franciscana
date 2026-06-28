package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.Usuario;
import tic.ordemFranciscana.backend.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuario.setId(null);
        return repository.save(usuario);
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
    public Usuario atualizar(Long id, Usuario novo) {
        if (!repository.existsById(id)) {
            return null;
        }
        novo.setId(id);
        return repository.save(novo);
    }
}
