package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Grupo;
import tic.ordemFranciscana.backend.service.GrupoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoService service;

    public GrupoController(GrupoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> listar() {
        List<Grupo> grupos = service.listar();

        if (grupos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> buscarPorId(@PathVariable Long id) {
        Optional<Grupo> grupoEncontrado = service.buscarPorId(id);

        if (grupoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(grupoEncontrado.get());
    }

    @PostMapping
    public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo) {
        Grupo novo = service.salvar(grupo);
        URI uri = URI.create("/grupos/" + novo.getId());

        return ResponseEntity.created(uri).body(novo);
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
    public ResponseEntity<Grupo> atualizar(@PathVariable Long id, @RequestBody Grupo novo) {
        Grupo atualizado = service.atualizar(id, novo);

        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }

        return ResponseEntity.notFound().build();
    }
}