package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.StatusReuniao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reuniao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "presencas", "documentos"})
public class Reuniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reuniao")
    private Long id;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reuniao", nullable = false, length = 30)
    private StatusReuniao status;

    @Column(name = "dt_reuniao", nullable = false)
    private LocalDateTime dataReuniao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    @JsonIgnoreProperties("reunioes")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_criador", nullable = false)
    @JsonIgnoreProperties({"reunioesCriadas", "senha"})
    private Usuario criadaPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa_concedida")
    @JsonIgnoreProperties("reunioes")
    private EtapaFormacao etapaConcedida;

    @OneToMany(mappedBy = "reuniao")
    private List<Presenca> presencas = new ArrayList<>();

    @OneToMany(mappedBy = "reuniao")
    private List<Documento> documentos = new ArrayList<>();

    public Reuniao() {
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

    public StatusReuniao getStatus() {
        return status;
    }

    public void setStatus(StatusReuniao status) {
        this.status = status;
    }

    public LocalDateTime getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(LocalDateTime dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getCriadaPor() {
        return criadaPor;
    }

    public void setCriadaPor(Usuario criadaPor) {
        this.criadaPor = criadaPor;
    }

    public EtapaFormacao getEtapaConcedida() {
        return etapaConcedida;
    }

    public void setEtapaConcedida(EtapaFormacao etapaConcedida) {
        this.etapaConcedida = etapaConcedida;
    }

    public List<Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<Presenca> presencas) {
        this.presencas = presencas;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}
