package com.eclesia.gestao_ministerial.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMembroDto {

    private UUID id;
    private String nomeCompleto;
    private String sexo;
    private String dataNascimento;
    private String estadoCivil;
    private CreateEnderecoDTO endereco;
    private String telefone;
    private String email;
    private String cargo;
    private String ministerio;
    private String celula;
    private boolean batizado;


}
