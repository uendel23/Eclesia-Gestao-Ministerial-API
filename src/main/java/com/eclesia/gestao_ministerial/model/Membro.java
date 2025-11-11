package com.eclesia.gestao_ministerial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@Table(name = "membros_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

}
