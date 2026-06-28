package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Aprovacao;
import tic.ordemFranciscana.backend.service.AprovacaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aprovacoes")
public class AprovacaoController {

    private final AprovacaoService service;

    public AprovacaoController(AprovacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Aprovacao>> listar() {
        List<Aprovacao> aprovacoes = service.listar();
        if (aprovacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aprovacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aprovacao> buscarPorId(@PathVariable Long id) {
        Optional<Aprovacao> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<Aprovacao> salvar(@RequestBody Aprovacao aprovacao) {
        Aprovacao nova = service.salvar(aprovacao);
        return ResponseEntity.created(URI.create("/aprovacoes/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Aprovacao> atualizar(@PathVariable Long id, @RequestBody Aprovacao aprovacao) {
        Aprovacao atualizada = service.atualizar(id, aprovacao);
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
