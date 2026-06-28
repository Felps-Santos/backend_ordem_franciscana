package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.ParticipacaoGrupo;
import tic.ordemFranciscana.backend.service.ParticipacaoGrupoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participacoes-grupo")
public class ParticipacaoGrupoController {

    private final ParticipacaoGrupoService service;

    public ParticipacaoGrupoController(ParticipacaoGrupoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ParticipacaoGrupo>> listar() {
        List<ParticipacaoGrupo> participacoes = service.listar();
        if (participacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(participacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipacaoGrupo> buscarPorId(@PathVariable Long id) {
        Optional<ParticipacaoGrupo> encontrada = service.buscarPorId(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrada.get());
    }

    @PostMapping
    public ResponseEntity<ParticipacaoGrupo> salvar(@RequestBody ParticipacaoGrupo participacao) {
        ParticipacaoGrupo nova = service.salvar(participacao);
        return ResponseEntity.created(URI.create("/participacoes-grupo/" + nova.getId())).body(nova);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ParticipacaoGrupo> atualizar(
            @PathVariable Long id, @RequestBody ParticipacaoGrupo participacao) {
        ParticipacaoGrupo atualizada = service.atualizar(id, participacao);
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
