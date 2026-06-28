package tic.ordemFranciscana.backend.model;

import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.TipoEntrada;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "entrada_financeira")
@PrimaryKeyJoinColumn(name = "id_entrada")
public class EntradaFinanceira extends MovimentacaoFinanceira {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entrada", nullable = false, length = 30)
    private TipoEntrada tipoEntrada;

    @Column(nullable = false, length = 200)
    private String origem;

    public EntradaFinanceira() {
    }

    public EntradaFinanceira(Long id, BigDecimal valor, LocalDate dataMovimentacao,
                             String descricao, Usuario registradaPor,
                             TipoEntrada tipoEntrada, String origem) {
        super(id, valor, dataMovimentacao, descricao, registradaPor);
        this.tipoEntrada = tipoEntrada;
        this.origem = origem;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}
