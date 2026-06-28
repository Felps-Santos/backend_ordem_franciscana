package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.StatusPresenca;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "presenca",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_reuniao", "id_membro"})
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presenca")
    private Long id;

    @Column(name = "dt_registro", nullable = false)
    private LocalDateTime dataRegistro;

    @Column(length = 500)
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_presenca", nullable = false, length = 30)
    private StatusPresenca status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reuniao", nullable = false)
    @JsonIgnoreProperties("presencas")
    private Reuniao reuniao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membro", nullable = false)
    @JsonIgnoreProperties("presencas")
    private Membro membro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_registro", nullable = false)
    @JsonIgnoreProperties({"presencasRegistradas", "senha"})
    private Usuario registradaPor;

    @OneToOne(mappedBy = "presenca")
    @JsonIgnoreProperties("presenca")
    private Justificativa justificativa;

    public Presenca() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusPresenca getStatus() {
        return status;
    }

    public void setStatus(StatusPresenca status) {
        this.status = status;
    }

    public Reuniao getReuniao() {
        return reuniao;
    }

    public void setReuniao(Reuniao reuniao) {
        this.reuniao = reuniao;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public Usuario getRegistradaPor() {
        return registradaPor;
    }

    public void setRegistradaPor(Usuario registradaPor) {
        this.registradaPor = registradaPor;
    }

    public Justificativa getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(Justificativa justificativa) {
        this.justificativa = justificativa;
    }
}
