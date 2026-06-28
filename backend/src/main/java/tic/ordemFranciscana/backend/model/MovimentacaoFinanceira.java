package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movimentacao_financeira")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class MovimentacaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(name = "dt_movimentacao", nullable = false)
    private LocalDate dataMovimentacao;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_registro", nullable = false)
    @JsonIgnoreProperties({"movimentacoes", "senha"})
    private Usuario registradaPor;

    protected MovimentacaoFinanceira() {
    }

    protected MovimentacaoFinanceira(Long id, BigDecimal valor, LocalDate dataMovimentacao,
                                     String descricao, Usuario registradaPor) {
        this.id = id;
        this.valor = valor;
        this.dataMovimentacao = dataMovimentacao;
        this.descricao = descricao;
        this.registradaPor = registradaPor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getRegistradaPor() {
        return registradaPor;
    }

    public void setRegistradaPor(Usuario registradaPor) {
        this.registradaPor = registradaPor;
    }
}
