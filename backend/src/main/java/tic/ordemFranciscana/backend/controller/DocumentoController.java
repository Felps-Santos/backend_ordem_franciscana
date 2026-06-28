package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.Documento;
import tic.ordemFranciscana.backend.service.DocumentoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Documento>> listar() {
        List<Documento> documentos = service.listar();
        if (documentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarPorId(@PathVariable Long id) {
        Optional<Documento> encontrado = service.buscarPorId(id);
        if (encontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado.get());
    }

    @PostMapping
    public ResponseEntity<Documento> salvar(@RequestBody Documento documento) {
        Documento novo = service.salvar(documento);
        return ResponseEntity.created(URI.create("/documentos/" + novo.getId())).body(novo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Documento> atualizar(@PathVariable Long id, @RequestBody Documento documento) {
        Documento atualizado = service.atualizar(id, documento);
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
