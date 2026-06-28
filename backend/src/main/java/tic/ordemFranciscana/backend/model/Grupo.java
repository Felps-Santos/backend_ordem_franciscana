package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo")
@JsonIgnoreProperties({
        "hibernateLazyInitializer", "handler", "participacoes",
        "reunioes", "etapas", "documentos"
})
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id;

    @Column(name = "nm_grupo", nullable = false, unique = true, length = 100)
    private String nome;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_criador", nullable = false)
    @JsonIgnoreProperties({"gruposCriados", "senha"})
    private Usuario criadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_administrador", nullable = false)
    @JsonIgnoreProperties({"gruposAdministrados", "senha"})
    private Usuario administrador;

    @OneToMany(mappedBy = "grupo")
    private List<ParticipacaoGrupo> participacoes = new ArrayList<>();

    @OneToMany(mappedBy = "grupo")
    private List<Reuniao> reunioes = new ArrayList<>();

    @OneToMany(mappedBy = "grupo")
    private List<EtapaFormacao> etapas = new ArrayList<>();

    @OneToMany(mappedBy = "grupo")
    private List<Documento> documentos = new ArrayList<>();

    public Grupo() {
    }

    public Grupo(Long id, String nome, String descricao, Usuario criadoPor, Usuario administrador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.criadoPor = criadoPor;
        this.administrador = administrador;
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

    public Usuario getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Usuario criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Usuario getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

    public List<ParticipacaoGrupo> getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(List<ParticipacaoGrupo> participacoes) {
        this.participacoes = participacoes;
    }

    public List<Reuniao> getReunioes() {
        return reunioes;
    }

    public void setReunioes(List<Reuniao> reunioes) {
        this.reunioes = reunioes;
    }

    public List<EtapaFormacao> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<EtapaFormacao> etapas) {
        this.etapas = etapas;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}
