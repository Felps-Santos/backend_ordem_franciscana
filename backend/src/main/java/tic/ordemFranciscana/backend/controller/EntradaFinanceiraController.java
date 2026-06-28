package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.EntradaFinanceira;
import tic.ordemFranciscana.backend.service.EntradaFinanceiraService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entradas-financeiras")
public class EntradaFinanceiraController {

    private final EntradaFinanceiraService service;

    public EntradaFinanceiraController(EntradaFinanceiraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EntradaFinanceira>> listar() {
        List<EntradaFinanceira> entradas = service.listar();
        if (entradas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaFinanceira> buscarPorId(@PathVariable Long id) {
        Optional<EntradaFinanceira> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<EntradaFinanceira> salvar(@RequestBody EntradaFinanceira entrada) {
        EntradaFinanceira nova = service.salvar(entrada);
        return ResponseEntity.created(URI.create("/entradas-financeiras/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntradaFinanceira> atualizar(
            @PathVariable Long id, @RequestBody EntradaFinanceira entrada) {
        EntradaFinanceira atualizada = service.atualizar(id, entrada);
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
