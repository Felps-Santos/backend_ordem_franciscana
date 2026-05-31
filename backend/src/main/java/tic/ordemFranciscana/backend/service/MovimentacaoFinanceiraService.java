package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import tic.ordemFranciscana.backend.model.MovimentacaoFinanceira;
import tic.ordemFranciscana.backend.repository.MovimentacaoFinanceiraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoFinanceiraService {

    private final MovimentacaoFinanceiraRepository repository;

    public MovimentacaoFinanceiraService(MovimentacaoFinanceiraRepository repository) {
        this.repository = repository;
    }

    public List<MovimentacaoFinanceira> listar() {
        return repository.findAll();
    }

    public Optional<MovimentacaoFinanceira> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public MovimentacaoFinanceira salvar(MovimentacaoFinanceira movimentacao) {
        movimentacao.setId(null);
        return repository.save(movimentacao);
    }

    public boolean remover(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    public MovimentacaoFinanceira atualizar(Long id, MovimentacaoFinanceira novo) {
        Optional<MovimentacaoFinanceira> movimentacaoEncontrada = repository.findById(id);

        if (movimentacaoEncontrada.isPresent()) {
            MovimentacaoFinanceira movimentacao = movimentacaoEncontrada.get();

            movimentacao.setValor(novo.getValor());
            movimentacao.setData(novo.getData());
            movimentacao.setDescricao(novo.getDescricao());
            movimentacao.setCategoria(novo.getCategoria());

            return repository.save(movimentacao);
        }

        return null;
    }
}