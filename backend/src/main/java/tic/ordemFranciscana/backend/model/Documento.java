package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.TipoDocumento;

@Entity
@Table(name = "documento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false, length = 30)
    private TipoDocumento tipo;

    @Column(name = "nm_arquivo", nullable = false, length = 255)
    private String nomeArquivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_gerador", nullable = false)
    @JsonIgnoreProperties({"documentosGerados", "senha"})
    private Usuario geradoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reuniao")
    @JsonIgnoreProperties("documentos")
    private Reuniao reuniao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo")
    @JsonIgnoreProperties("documentos")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao")
    @JsonIgnoreProperties("documentos")
    private Solicitacao solicitacao;

    public Documento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Usuario getGeradoPor() {
        return geradoPor;
    }

    public void setGeradoPor(Usuario geradoPor) {
        this.geradoPor = geradoPor;
    }

    public Reuniao getReuniao() {
        return reuniao;
    }

    public void setReuniao(Reuniao reuniao) {
        this.reuniao = reuniao;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}
