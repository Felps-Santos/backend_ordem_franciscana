package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.EtapaFormacao;
import tic.ordemFranciscana.backend.service.EtapaFormacaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etapas-formacao")
public class EtapaFormacaoController {

    private final EtapaFormacaoService service;

    public EtapaFormacaoController(EtapaFormacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EtapaFormacao>> listar() {
        List<EtapaFormacao> etapas = service.listar();
        if (etapas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(etapas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtapaFormacao> buscarPorId(@PathVariable Long id) {
        Optional<EtapaFormacao> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<EtapaFormacao> salvar(@RequestBody EtapaFormacao etapa) {
        EtapaFormacao nova = service.salvar(etapa);
        return ResponseEntity.created(URI.create("/etapas-formacao/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EtapaFormacao> atualizar(
            @PathVariable Long id, @RequestBody EtapaFormacao etapa) {
        EtapaFormacao atualizada = service.atualizar(id, etapa);
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
