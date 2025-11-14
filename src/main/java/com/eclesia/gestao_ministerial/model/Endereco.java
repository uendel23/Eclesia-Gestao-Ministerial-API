package com.eclesia.gestao_ministerial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@Table(name = "endereco_tb")
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String cep;

    @NotBlank
    private String rua;

    @NotBlank
    private String numero;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank String estado) {
        this.estado = estado;
    }

    public @NotBlank String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank String cidade) {
        this.cidade = cidade;
    }

    public @NotBlank String getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank String numero) {
        this.numero = numero;
    }

    public @NotBlank String getRua() {
        return rua;
    }

    public void setRua(@NotBlank String rua) {
        this.rua = rua;
    }

    @NotBlank
    public String getCep() {
        return cep;
    }

    public void setCep(@NotBlank String cep) {
        this.cep = cep;
    }
}
