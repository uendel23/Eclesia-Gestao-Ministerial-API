package com.eclesia.gestao_ministerial.model;
import com.eclesia.gestao_ministerial.enums.StatusMembro;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.Id;
import org.hibernate.annotations.SQLRestriction;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "membros_tb")
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("excluido = false")
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagem_id", referencedColumnName = "id")
    private Imagem imagem;

    @NotBlank
    private String cpf;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String sexo;

    @NotNull(message = "A data de nascimento é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank
    private String EstadoCivil;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @NotBlank
    private String telefone;

    @NotBlank
    private String email;

    private String cargo;

    private String ministerio;

    private String congregacao;

    private boolean batizado;

    @Column(name = "excluido", nullable = false)
    private boolean excluido=false;

    @Enumerated(EnumType.STRING)
    private StatusMembro status = StatusMembro.ATIVO;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public boolean gitExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public StatusMembro getStatus() {
        return status;
    }

    public void setStatus(StatusMembro status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(@NotBlank String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public @NotBlank String getSexo() {
        return sexo;
    }

    public void setSexo(@NotBlank String sexo) {
        this.sexo = sexo;
    }

    public  LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento( LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @NotBlank String getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(@NotBlank String estadoCivil) {
        EstadoCivil = estadoCivil;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public @NotBlank String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank String telefone) {
        this.telefone = telefone;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(String ministerio) {
        this.ministerio = ministerio;
    }

    public boolean isBatizado() {
        return batizado;
    }

    public void setBatizado(boolean batizado) {
        this.batizado = batizado;
    }

    public String getCongregacao() {
        return congregacao;
    }

    public void setCongregacao(String congregacao) {
        this.congregacao = congregacao;
    }
}
