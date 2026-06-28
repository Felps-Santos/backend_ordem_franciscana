package tic.ordemFranciscana.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tic.ordemFranciscana.backend.model.UsuarioFuncao;
import tic.ordemFranciscana.backend.service.UsuarioFuncaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios-funcoes")
public class UsuarioFuncaoController {

    private final UsuarioFuncaoService service;

    public UsuarioFuncaoController(UsuarioFuncaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioFuncao>> listar() {
        List<UsuarioFuncao> registros = service.listar();
        if (registros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioFuncao> buscarPorId(@PathVariable Long id) {
        Optional<UsuarioFuncao> encontrado = service.buscarPorId(id);
        if (encontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado.get());
    }

    @PostMapping
    public ResponseEntity<UsuarioFuncao> salvar(@RequestBody UsuarioFuncao usuarioFuncao) {
        UsuarioFuncao novo = service.salvar(usuarioFuncao);
        return ResponseEntity.created(URI.create("/usuarios-funcoes/" + novo.getId())).body(novo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioFuncao> atualizar(
            @PathVariable Long id, @RequestBody UsuarioFuncao usuarioFuncao) {
        UsuarioFuncao atualizado = service.atualizar(id, usuarioFuncao);
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
