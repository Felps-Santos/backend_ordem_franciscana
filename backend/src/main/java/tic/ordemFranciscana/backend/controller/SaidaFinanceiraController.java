package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.SaidaFinanceira;
import tic.ordemFranciscana.backend.service.SaidaFinanceiraService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saidas-financeiras")
public class SaidaFinanceiraController {

    private final SaidaFinanceiraService service;

    public SaidaFinanceiraController(SaidaFinanceiraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SaidaFinanceira>> listar() {
        List<SaidaFinanceira> saidas = service.listar();
        if (saidas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(saidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaFinanceira> buscarPorId(@PathVariable Long id) {
        Optional<SaidaFinanceira> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<SaidaFinanceira> salvar(@RequestBody SaidaFinanceira saida) {
        SaidaFinanceira nova = service.salvar(saida);
        return ResponseEntity.created(URI.create("/saidas-financeiras/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SaidaFinanceira> atualizar(
            @PathVariable Long id, @RequestBody SaidaFinanceira saida) {
        SaidaFinanceira atualizada = service.atualizar(id, saida);
        if (atualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!service.remover(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
