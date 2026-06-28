package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Justificativa;
import tic.ordemFranciscana.backend.service.JustificativaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/justificativas")
public class JustificativaController {

    private final JustificativaService service;

    public JustificativaController(JustificativaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Justificativa>> listar() {
        List<Justificativa> justificativas = service.listar();
        if (justificativas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(justificativas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Justificativa> buscarPorId(@PathVariable Long id) {
        Optional<Justificativa> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<Justificativa> salvar(@RequestBody Justificativa justificativa) {
        Justificativa nova = service.salvar(justificativa);
        return ResponseEntity.created(URI.create("/justificativas/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Justificativa> atualizar(
            @PathVariable Long id, @RequestBody Justificativa justificativa) {
        Justificativa atualizada = service.atualizar(id, justificativa);
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
