package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "etapa_formacao",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_grupo", "ordem_etapa"})
)
@JsonIgnoreProperties({
        "hibernateLazyInitializer", "handler", "historicos",
        "solicitacoes", "reunioes"
})
public class EtapaFormacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etapa")
    private Long id;

    @Column(name = "nome_etapa", nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(name = "ordem_etapa", nullable = false)
    private Integer ordem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    @JsonIgnoreProperties("etapas")
    private Grupo grupo;

    @OneToMany(mappedBy = "etapa")
    private List<HistoricoFormacao> historicos = new ArrayList<>();

    @OneToMany(mappedBy = "etapaSolicitada")
    private List<Solicitacao> solicitacoes = new ArrayList<>();

    @OneToMany(mappedBy = "etapaConcedida")
    private List<Reuniao> reunioes = new ArrayList<>();

    public EtapaFormacao() {
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

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<HistoricoFormacao> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HistoricoFormacao> historicos) {
        this.historicos = historicos;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public List<Reuniao> getReunioes() {
        return reunioes;
    }

    public void setReunioes(List<Reuniao> reunioes) {
        this.reunioes = reunioes;
    }
}
