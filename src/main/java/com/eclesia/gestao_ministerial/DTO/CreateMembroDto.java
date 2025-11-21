package com.eclesia.gestao_ministerial.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;



public class CreateMembroDto {

    private UUID id;
    private String cpf;
    private String nomeCompleto;
    private String sexo;
    private String dataNascimento;
    private String estadoCivil;
    private CreateEnderecoDto endereco;
    private String telefone;
    private String email;
    private String cargo;
    private String ministerio;
    private String celula;
    private boolean batizado;
    private Boolean ativo;

    public CreateMembroDto() {
    }

    public CreateMembroDto(UUID id, String cpf, String nomeCompleto, String sexo, String dataNascimento, String estadoCivil, CreateEnderecoDto endereco, String telefone, String email, String cargo, String ministerio, String celula, boolean batizado, Boolean ativo) {
        this.id = id;
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.estadoCivil = estadoCivil;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cargo = cargo;
        this.ministerio = ministerio;
        this.celula = celula;
        this.batizado = batizado;
        this.ativo = ativo;
    }

    public CreateMembroDto(UUID id, @NotBlank String nomeCompleto, String cargo, String ministerio, Boolean ativo) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cargo = cargo;
        this.ministerio = ministerio;
        this.ativo = ativo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public CreateEnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(CreateEnderecoDto endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public String getCelula() {
        return celula;
    }

    public void setCelula(String celula) {
        this.celula = celula;
    }

    public boolean isBatizado() {
        return batizado;
    }

    public void setBatizado(boolean batizado) {
        this.batizado = batizado;
    }
}
