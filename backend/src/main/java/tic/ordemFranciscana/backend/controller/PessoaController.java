package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Pessoa;
import tic.ordemFranciscana.backend.service.PessoaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        List<Pessoa> pessoas = service.listar();

        if (pessoas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoaEncontrada = service.buscarPorId(id);

        if (pessoaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pessoaEncontrada.get());
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
        Pessoa nova = service.salvar(pessoa);
        URI uri = URI.create("/pessoas/" + nova.getId());

        return ResponseEntity.created(uri).body(nova);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        boolean status = service.remover(id);

        if (status) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa nova) {
        Pessoa atualizada = service.atualizar(id, nova);

        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }

        return ResponseEntity.notFound().build();
    }
}