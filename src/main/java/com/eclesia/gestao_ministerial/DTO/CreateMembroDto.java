package com.eclesia.gestao_ministerial.DTO;

import com.eclesia.gestao_ministerial.enums.StatusMembro;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;



public class CreateMembroDto {

    private UUID id;

    private String cpf;

    private String nomeCompleto;

    private String sexo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String estadoCivil;

    private EnderecoDto endereco;

    private ImagemDto imagem;

    private String telefone;

    private String email;

    private String cargo;

    private String ministerio;

    private String congregacao;

    private boolean batizado;

    private StatusMembro status;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public CreateMembroDto() {
    }

    public CreateMembroDto( ImagemDto imagem, String cpf, String nomeCompleto, String sexo, LocalDate dataNascimento,
                           String estadoCivil, EnderecoDto endereco, String telefone, String email,
                           String cargo, String ministerio, String congregacao, boolean batizado, StatusMembro status, LocalDate dataCadastro) {
        this.imagem =  imagem;
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
        this.congregacao = congregacao;
        this.batizado = batizado;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    public CreateMembroDto(UUID id, @NotBlank String nomeCompleto, String cargo, String ministerio, StatusMembro status) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cargo = cargo;
        this.ministerio = ministerio;
        this.status = status;
    }

    public ImagemDto getImagem() {
        return imagem;
    }

    public void setImagem(ImagemDto imagem) {
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
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

    public String getCongregacao() {
        return congregacao;
    }

    public void setCongregacao(String celula) {
        this.congregacao = celula;
    }

    public boolean isBatizado() {
        return batizado;
    }

    public void setBatizado(boolean batizado) {
        this.batizado = batizado;
    }
}
