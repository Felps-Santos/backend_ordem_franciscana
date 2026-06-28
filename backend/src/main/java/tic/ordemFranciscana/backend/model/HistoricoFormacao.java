package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "historico_formacao")
public class HistoricoFormacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Long id;

    @Column(name = "dt_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "dt_fim")
    private LocalDate dataFim;

    @Column(length = 500)
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membro", nullable = false)
    @JsonIgnoreProperties("historicosFormacao")
    private Membro membro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa", nullable = false)
    @JsonIgnoreProperties("historicos")
    private EtapaFormacao etapa;

    public HistoricoFormacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public EtapaFormacao getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaFormacao etapa) {
        this.etapa = etapa;
    }
}
