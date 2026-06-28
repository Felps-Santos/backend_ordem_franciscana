package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "usuarios"})
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcao")
    private Long id;

    @Column(name = "nm_funcao", nullable = false, unique = true, length = 100)
    private String nome;

    @Column(name = "descricao_funcao", length = 500)
    private String descricao;

    @OneToMany(mappedBy = "funcao")
    private List<UsuarioFuncao> usuarios = new ArrayList<>();

    public Funcao() {
    }

    public Funcao(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<UsuarioFuncao> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioFuncao> usuarios) {
        this.usuarios = usuarios;
    }
}
