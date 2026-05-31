package tic.ordemFranciscana.backend.service;

import org.springframework.stereotype.Service;
import tic.ordemFranciscana.backend.model.Pessoa;
import tic.ordemFranciscana.backend.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> listar() {
        return repository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Pessoa salvar(Pessoa pessoa) {
        pessoa.setId(null);
        return repository.save(pessoa);
    }

    public boolean remover(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    public Pessoa atualizar(Long id, Pessoa novo) {
        Optional<Pessoa> pessoaEncontrada = repository.findById(id);

        if (pessoaEncontrada.isPresent()) {
            Pessoa pessoa = pessoaEncontrada.get();

            pessoa.setNome(novo.getNome());
            pessoa.setCpf(novo.getCpf());
            pessoa.setDataNascimento(novo.getDataNascimento());
            pessoa.setTelefone(novo.getTelefone());
            pessoa.setEmail(novo.getEmail());

            return repository.save(pessoa);
        }

        return null;
    }
}