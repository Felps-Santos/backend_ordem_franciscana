package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Funcao;
import tic.ordemFranciscana.backend.service.FuncaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcoes")
public class FuncaoController {

    private final FuncaoService service;

    public FuncaoController(FuncaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Funcao>> listar() {
        List<Funcao> funcoes = service.listar();
        if (funcoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcao> buscarPorId(@PathVariable Long id) {
        Optional<Funcao> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<Funcao> salvar(@RequestBody Funcao funcao) {
        Funcao nova = service.salvar(funcao);
        return ResponseEntity.created(URI.create("/funcoes/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Funcao> atualizar(@PathVariable Long id, @RequestBody Funcao funcao) {
        Funcao atualizada = service.atualizar(id, funcao);
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
