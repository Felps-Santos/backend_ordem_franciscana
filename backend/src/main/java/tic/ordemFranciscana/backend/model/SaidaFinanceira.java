package tic.ordemFranciscana.backend.model;

import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.TipoSaida;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saida_financeira")
@PrimaryKeyJoinColumn(name = "id_saida")
public class SaidaFinanceira extends MovimentacaoFinanceira {

    @Column(nullable = false, length = 200)
    private String destino;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_saida", nullable = false, length = 30)
    private TipoSaida tipoSaida;

    public SaidaFinanceira() {
    }

    public SaidaFinanceira(Long id, BigDecimal valor, LocalDate dataMovimentacao,
                           String descricao, Usuario registradaPor,
                           String destino, TipoSaida tipoSaida) {
        super(id, valor, dataMovimentacao, descricao, registradaPor);
        this.destino = destino;
        this.tipoSaida = tipoSaida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public TipoSaida getTipoSaida() {
        return tipoSaida;
    }

    public void setTipoSaida(TipoSaida tipoSaida) {
        this.tipoSaida = tipoSaida;
    }
}
