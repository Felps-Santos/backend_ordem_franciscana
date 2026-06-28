package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.DecisaoConselho;

import java.time.LocalDateTime;

@Entity
@Table(name = "aprovacao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aprovacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aprovacao")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "decisao_conselho", nullable = false, length = 30)
    private DecisaoConselho decisaoConselho;

    @Column(name = "dt_registro", nullable = false)
    private LocalDateTime dataRegistro;

    @Column(length = 1000)
    private String parecer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao", nullable = false, unique = true)
    @JsonIgnoreProperties("aprovacao")
    private Solicitacao solicitacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_registro", nullable = false)
    @JsonIgnoreProperties({"aprovacoesRegistradas", "senha"})
    private Usuario registradaPor;

    public Aprovacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DecisaoConselho getDecisaoConselho() {
        return decisaoConselho;
    }

    public void setDecisaoConselho(DecisaoConselho decisaoConselho) {
        this.decisaoConselho = decisaoConselho;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Usuario getRegistradaPor() {
        return registradaPor;
    }

    public void setRegistradaPor(Usuario registradaPor) {
        this.registradaPor = registradaPor;
    }
}
