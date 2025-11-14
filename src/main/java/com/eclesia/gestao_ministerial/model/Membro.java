package com.eclesia.gestao_ministerial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@Table(name = "membros_tb")
@AllArgsConstructor
@NoArgsConstructor
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String cpf;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String sexo;
    @NotBlank
    private String dataNascimento;

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

    private String celula;

    private boolean batizado;

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

    public @NotBlank String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotBlank String dataNascimento) {
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

    public String getCelula() {
        return celula;
    }

    public void setCelula(String celula) {
        this.celula = celula;
    }
}
