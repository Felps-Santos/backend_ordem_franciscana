package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.TipoSolicitacao;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitacao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "documentos"})
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_solicitacao", nullable = false, length = 50)
    private TipoSolicitacao tipo;

    @Column(name = "parecer_formador", length = 1000)
    private String parecerFormador;

    @Column(nullable = false, length = 500)
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membro", nullable = false)
    @JsonIgnoreProperties("solicitacoes")
    private Membro membro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa_solicitada")
    @JsonIgnoreProperties("solicitacoes")
    private EtapaFormacao etapaSolicitada;

    @OneToOne(mappedBy = "solicitacao")
    @JsonIgnoreProperties("solicitacao")
    private Aprovacao aprovacao;

    @OneToMany(mappedBy = "solicitacao")
    private List<Documento> documentos = new ArrayList<>();

    public Solicitacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoSolicitacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoSolicitacao tipo) {
        this.tipo = tipo;
    }

    public String getParecerFormador() {
        return parecerFormador;
    }

    public void setParecerFormador(String parecerFormador) {
        this.parecerFormador = parecerFormador;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public EtapaFormacao getEtapaSolicitada() {
        return etapaSolicitada;
    }

    public void setEtapaSolicitada(EtapaFormacao etapaSolicitada) {
        this.etapaSolicitada = etapaSolicitada;
    }

    public Aprovacao getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(Aprovacao aprovacao) {
        this.aprovacao = aprovacao;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}
