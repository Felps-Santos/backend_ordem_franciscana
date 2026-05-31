package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import tic.ordemFranciscana.backend.model.Reuniao;
import tic.ordemFranciscana.backend.repository.ReuniaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReuniaoService {

    private final ReuniaoRepository repository;

    public ReuniaoService(ReuniaoRepository repository) {
        this.repository = repository;
    }

    public List<Reuniao> listar() {
        return repository.findAll();
    }

    public Optional<Reuniao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Reuniao salvar(Reuniao reuniao) {
        reuniao.setId(null);
        return repository.save(reuniao);
    }

    public boolean remover(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    public Reuniao atualizar(Long id, Reuniao novo) {
        Optional<Reuniao> reuniaoEncontrada = repository.findById(id);

        if (reuniaoEncontrada.isPresent()) {
            Reuniao reuniao = reuniaoEncontrada.get();

            reuniao.setData(novo.getData());
            reuniao.setHora(novo.getHora());
            reuniao.setDescricao(novo.getDescricao());

            return repository.save(reuniao);
        }

        return null;
    }
}