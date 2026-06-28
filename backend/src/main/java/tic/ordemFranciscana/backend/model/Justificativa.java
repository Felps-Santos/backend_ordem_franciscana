package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.StatusJustificativa;

import java.time.LocalDate;

@Entity
@Table(name = "justificativa")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Justificativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_justificativa")
    private Long id;

    @Column(nullable = false, length = 500)
    private String motivo;

    @Column(name = "dt_justificativa", nullable = false)
    private LocalDate dataJustificativa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_justificativa", nullable = false, length = 30)
    private StatusJustificativa status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_presenca", nullable = false, unique = true)
    @JsonIgnoreProperties("justificativa")
    private Presenca presenca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_analise")
    @JsonIgnoreProperties({"justificativasAnalisadas", "senha"})
    private Usuario analisadaPor;

    public Justificativa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getDataJustificativa() {
        return dataJustificativa;
    }

    public void setDataJustificativa(LocalDate dataJustificativa) {
        this.dataJustificativa = dataJustificativa;
    }

    public StatusJustificativa getStatus() {
        return status;
    }

    public void setStatus(StatusJustificativa status) {
        this.status = status;
    }

    public Presenca getPresenca() {
        return presenca;
    }

    public void setPresenca(Presenca presenca) {
        this.presenca = presenca;
    }

    public Usuario getAnalisadaPor() {
        return analisadaPor;
    }

    public void setAnalisadaPor(Usuario analisadaPor) {
        this.analisadaPor = analisadaPor;
    }
}
