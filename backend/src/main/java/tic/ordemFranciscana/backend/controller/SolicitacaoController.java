package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Solicitacao;
import tic.ordemFranciscana.backend.service.SolicitacaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoService service;

    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Solicitacao>> listar() {
        List<Solicitacao> solicitacoes = service.listar();
        if (solicitacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> buscarPorId(@PathVariable Long id) {
        Optional<Solicitacao> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<Solicitacao> salvar(@RequestBody Solicitacao solicitacao) {
        Solicitacao nova = service.salvar(solicitacao);
        return ResponseEntity.created(URI.create("/solicitacoes/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Solicitacao> atualizar(
            @PathVariable Long id, @RequestBody Solicitacao solicitacao) {
        Solicitacao atualizada = service.atualizar(id, solicitacao);
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
