package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Membro;
import tic.ordemFranciscana.backend.service.MembroService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/membros")
public class MembroController {

    private final MembroService service;

    public MembroController(MembroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Membro>> listar() {
        List<Membro> membros = service.listar();
        if (membros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(membros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> buscarPorId(@PathVariable Long id) {
        Optional<Membro> encontrado = service.buscarPorId(id);
        if (encontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado.get());
    }

    @PostMapping
    public ResponseEntity<Membro> salvar(@RequestBody Membro membro) {
        Membro novo = service.salvar(membro);
        return ResponseEntity.created(URI.create("/membros/" + novo.getId())).body(novo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Membro> atualizar(@PathVariable Long id, @RequestBody Membro membro) {
        Membro atualizado = service.atualizar(id, membro);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!service.remover(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
