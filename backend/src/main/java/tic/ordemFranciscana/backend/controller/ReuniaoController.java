package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Reuniao;
import tic.ordemFranciscana.backend.service.ReuniaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reunioes")
public class ReuniaoController {

    private final ReuniaoService service;

    public ReuniaoController(ReuniaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Reuniao>> listar() {
        List<Reuniao> reunioes = service.listar();

        if (reunioes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reunioes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reuniao> buscarPorId(@PathVariable Long id) {
        Optional<Reuniao> reuniaoEncontrada = service.buscarPorId(id);

        if (reuniaoEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reuniaoEncontrada.get());
    }

    @PostMapping
    public ResponseEntity<Reuniao> salvar(@RequestBody Reuniao reuniao) {
        Reuniao nova = service.salvar(reuniao);
        URI uri = URI.create("/reunioes/" + nova.getId());

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
    public ResponseEntity<Reuniao> atualizar(@PathVariable Long id, @RequestBody Reuniao nova) {
        Reuniao atualizada = service.atualizar(id, nova);

        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }

        return ResponseEntity.notFound().build();
    }
}