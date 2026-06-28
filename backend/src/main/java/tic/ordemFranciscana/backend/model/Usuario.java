package tic.ordemFranciscana.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "id_usuario")
@JsonIgnoreProperties({
        "hibernateLazyInitializer", "handler", "usuarioFuncoes",
        "movimentacoes", "aprovacoesRegistradas", "presencasRegistradas",
        "membrosCadastrados", "gruposCriados", "gruposAdministrados",
        "reunioesCriadas", "documentosGerados", "justificativasAnalisadas"
})
public class Usuario extends Pessoa {

    @Column(nullable = false, unique = true, length = 100)
    private String login;

    @Column(nullable = false, length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioFuncao> usuarioFuncoes = new ArrayList<>();

    @OneToMany(mappedBy = "registradaPor")
    private List<MovimentacaoFinanceira> movimentacoes = new ArrayList<>();

    @OneToMany(mappedBy = "registradaPor")
    private List<Aprovacao> aprovacoesRegistradas = new ArrayList<>();

    @OneToMany(mappedBy = "registradaPor")
    private List<Presenca> presencasRegistradas = new ArrayList<>();

    @OneToMany(mappedBy = "cadastradoPor")
    private List<Membro> membrosCadastrados = new ArrayList<>();

    @OneToMany(mappedBy = "criadoPor")
    private List<Grupo> gruposCriados = new ArrayList<>();

    @OneToMany(mappedBy = "administrador")
    private List<Grupo> gruposAdministrados = new ArrayList<>();

    @OneToMany(mappedBy = "criadaPor")
    private List<Reuniao> reunioesCriadas = new ArrayList<>();

    @OneToMany(mappedBy = "geradoPor")
    private List<Documento> documentosGerados = new ArrayList<>();

    @OneToMany(mappedBy = "analisadaPor")
    private List<Justificativa> justificativasAnalisadas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, LocalDate dataNascimento, String cpf,
                   String login, String senha, Boolean ativo) {
        super(id, nome, dataNascimento, cpf);
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<UsuarioFuncao> getUsuarioFuncoes() {
        return usuarioFuncoes;
    }

    public void setUsuarioFuncoes(List<UsuarioFuncao> usuarioFuncoes) {
        this.usuarioFuncoes = usuarioFuncoes;
    }

    public List<MovimentacaoFinanceira> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoFinanceira> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<Aprovacao> getAprovacoesRegistradas() {
        return aprovacoesRegistradas;
    }

    public void setAprovacoesRegistradas(List<Aprovacao> aprovacoesRegistradas) {
        this.aprovacoesRegistradas = aprovacoesRegistradas;
    }

    public List<Presenca> getPresencasRegistradas() {
        return presencasRegistradas;
    }

    public void setPresencasRegistradas(List<Presenca> presencasRegistradas) {
        this.presencasRegistradas = presencasRegistradas;
    }

    public List<Membro> getMembrosCadastrados() {
        return membrosCadastrados;
    }

    public void setMembrosCadastrados(List<Membro> membrosCadastrados) {
        this.membrosCadastrados = membrosCadastrados;
    }

    public List<Grupo> getGruposCriados() {
        return gruposCriados;
    }

    public void setGruposCriados(List<Grupo> gruposCriados) {
        this.gruposCriados = gruposCriados;
    }

    public List<Grupo> getGruposAdministrados() {
        return gruposAdministrados;
    }

    public void setGruposAdministrados(List<Grupo> gruposAdministrados) {
        this.gruposAdministrados = gruposAdministrados;
    }

    public List<Reuniao> getReunioesCriadas() {
        return reunioesCriadas;
    }

    public void setReunioesCriadas(List<Reuniao> reunioesCriadas) {
        this.reunioesCriadas = reunioesCriadas;
    }

    public List<Documento> getDocumentosGerados() {
        return documentosGerados;
    }

    public void setDocumentosGerados(List<Documento> documentosGerados) {
        this.documentosGerados = documentosGerados;
    }

    public List<Justificativa> getJustificativasAnalisadas() {
        return justificativasAnalisadas;
    }

    public void setJustificativasAnalisadas(List<Justificativa> justificativasAnalisadas) {
        this.justificativasAnalisadas = justificativasAnalisadas;
    }
}
