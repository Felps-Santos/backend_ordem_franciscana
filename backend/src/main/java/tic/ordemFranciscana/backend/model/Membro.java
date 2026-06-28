package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.StatusMembro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "membro")
@PrimaryKeyJoinColumn(name = "id_membro")
@JsonIgnoreProperties({
        "hibernateLazyInitializer", "handler", "participacoes", "presencas",
        "solicitacoes", "historicosFormacao"
})
public class Membro extends Pessoa {

    @Column(length = 500)
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_membro", nullable = false, length = 30)
    private StatusMembro statusMembro;

    @Column(name = "dt_ingresso", nullable = false)
    private LocalDate dataIngresso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cadastro", nullable = false)
    @JsonIgnoreProperties({"membrosCadastrados", "senha"})
    private Usuario cadastradoPor;

    @OneToMany(mappedBy = "membro")
    private List<ParticipacaoGrupo> participacoes = new ArrayList<>();

    @OneToMany(mappedBy = "membro")
    private List<Presenca> presencas = new ArrayList<>();

    @OneToMany(mappedBy = "membro")
    private List<Solicitacao> solicitacoes = new ArrayList<>();

    @OneToMany(mappedBy = "membro")
    private List<HistoricoFormacao> historicosFormacao = new ArrayList<>();

    public Membro() {
    }

    public Membro(Long id, String nome, LocalDate dataNascimento, String cpf,
                  String observacao, StatusMembro statusMembro,
                  LocalDate dataIngresso, Usuario cadastradoPor) {
        super(id, nome, dataNascimento, cpf);
        this.observacao = observacao;
        this.statusMembro = statusMembro;
        this.dataIngresso = dataIngresso;
        this.cadastradoPor = cadastradoPor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusMembro getStatusMembro() {
        return statusMembro;
    }

    public void setStatusMembro(StatusMembro statusMembro) {
        this.statusMembro = statusMembro;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Usuario getCadastradoPor() {
        return cadastradoPor;
    }

    public void setCadastradoPor(Usuario cadastradoPor) {
        this.cadastradoPor = cadastradoPor;
    }

    public List<ParticipacaoGrupo> getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(List<ParticipacaoGrupo> participacoes) {
        this.participacoes = participacoes;
    }

    public List<Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<Presenca> presencas) {
        this.presencas = presencas;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public List<HistoricoFormacao> getHistoricosFormacao() {
        return historicosFormacao;
    }

    public void setHistoricosFormacao(List<HistoricoFormacao> historicosFormacao) {
        this.historicosFormacao = historicosFormacao;
    }
}
