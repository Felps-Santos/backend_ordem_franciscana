package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.HistoricoFormacao;
import tic.ordemFranciscana.backend.service.HistoricoFormacaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historicos-formacao")
public class HistoricoFormacaoController {

    private final HistoricoFormacaoService service;

    public HistoricoFormacaoController(HistoricoFormacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HistoricoFormacao>> listar() {
        List<HistoricoFormacao> historicos = service.listar();
        if (historicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoFormacao> buscarPorId(@PathVariable Long id) {
        Optional<HistoricoFormacao> encontrado = service.buscarPorId(id);
        if (encontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado.get());
    }

    @PostMapping
    public ResponseEntity<HistoricoFormacao> salvar(@RequestBody HistoricoFormacao historico) {
        HistoricoFormacao novo = service.salvar(historico);
        return ResponseEntity.created(URI.create("/historicos-formacao/" + novo.getId())).body(novo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HistoricoFormacao> atualizar(
            @PathVariable Long id, @RequestBody HistoricoFormacao historico) {
        HistoricoFormacao atualizado = service.atualizar(id, historico);
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
