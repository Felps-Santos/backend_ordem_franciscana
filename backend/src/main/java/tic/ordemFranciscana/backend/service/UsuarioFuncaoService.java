package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tic.ordemFranciscana.backend.model.UsuarioFuncao;
import tic.ordemFranciscana.backend.repository.UsuarioFuncaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsuarioFuncaoService {

    private final UsuarioFuncaoRepository repository;

    public UsuarioFuncaoService(UsuarioFuncaoRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioFuncao> listar() {
        return repository.findAll();
    }

    public Optional<UsuarioFuncao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public UsuarioFuncao salvar(UsuarioFuncao usuarioFuncao) {
        usuarioFuncao.setId(null);
        return repository.save(usuarioFuncao);
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
    public UsuarioFuncao atualizar(Long id, UsuarioFuncao nova) {
        if (!repository.existsById(id)) {
            return null;
        }
        nova.setId(id);
        return repository.save(nova);
    }
}
