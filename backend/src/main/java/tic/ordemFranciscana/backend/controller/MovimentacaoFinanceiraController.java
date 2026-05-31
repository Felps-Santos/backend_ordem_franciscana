package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.MovimentacaoFinanceira;
import tic.ordemFranciscana.backend.service.MovimentacaoFinanceiraService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimentacoes-financeiras")
public class MovimentacaoFinanceiraController {

    private final MovimentacaoFinanceiraService service;

    public MovimentacaoFinanceiraController(MovimentacaoFinanceiraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoFinanceira>> listar() {
        List<MovimentacaoFinanceira> movimentacoes = service.listar();

        if (movimentacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoFinanceira> buscarPorId(@PathVariable Long id) {
        Optional<MovimentacaoFinanceira> movimentacaoEncontrada = service.buscarPorId(id);

        if (movimentacaoEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movimentacaoEncontrada.get());
    }

    @PostMapping
    public ResponseEntity<MovimentacaoFinanceira> salvar(@RequestBody MovimentacaoFinanceira movimentacao) {
        MovimentacaoFinanceira nova = service.salvar(movimentacao);
        URI uri = URI.create("/movimentacoes-financeiras/" + nova.getId());

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
    public ResponseEntity<MovimentacaoFinanceira> atualizar(
            @PathVariable Long id,
            @RequestBody MovimentacaoFinanceira nova
    ) {
        MovimentacaoFinanceira atualizada = service.atualizar(id, nova);

        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }

        return ResponseEntity.notFound().build();
    }
}