package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import tic.ordemFranciscana.backend.model.enums.StatusFuncaoUsuario;

import java.time.LocalDate;

@Entity
@Table(
        name = "usuario_funcao",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_funcao", "dt_inicio"})
)
public class UsuarioFuncao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_funcao")
    private Long id;

    @Column(name = "dt_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "dt_fim")
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_funcao_usuario", nullable = false, length = 30)
    private StatusFuncaoUsuario status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"usuarioFuncoes", "senha"})
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcao", nullable = false)
    @JsonIgnoreProperties("usuarios")
    private Funcao funcao;

    public UsuarioFuncao() {
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

    public StatusFuncaoUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusFuncaoUsuario status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
}
