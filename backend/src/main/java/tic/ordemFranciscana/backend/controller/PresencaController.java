package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Presenca;
import tic.ordemFranciscana.backend.service.PresencaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/presencas")
public class PresencaController {

    private final PresencaService service;

    public PresencaController(PresencaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Presenca>> listar() {
        List<Presenca> presencas = service.listar();
        if (presencas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(presencas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presenca> buscarPorId(@PathVariable Long id) {
        Optional<Presenca> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<Presenca> salvar(@RequestBody Presenca presenca) {
        Presenca nova = service.salvar(presenca);
        return ResponseEntity.created(URI.create("/presencas/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Presenca> atualizar(@PathVariable Long id, @RequestBody Presenca presenca) {
        Presenca atualizada = service.atualizar(id, presenca);
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
