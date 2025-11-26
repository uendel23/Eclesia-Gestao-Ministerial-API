package com.eclesia.gestao_ministerial.service;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.enums.StatusMembro;
import com.eclesia.gestao_ministerial.exception.MembroException;
import com.eclesia.gestao_ministerial.mapper.MembroMapper;
import com.eclesia.gestao_ministerial.model.Imagem;
import com.eclesia.gestao_ministerial.model.Membro;
import com.eclesia.gestao_ministerial.repository.ImagemRepository;
import com.eclesia.gestao_ministerial.repository.MembroRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MembroService {

  private List<Membro> membrosDeletados = new ArrayList<>();
  @Autowired
  ImagemService imagemService;
  @Autowired
  ImagemRepository imagemRepository;

    public MembroService(MembroRepository membroRepository, MembroMapper membroMapper) {
        this.membroRepository = membroRepository;
        this.membroMapper = membroMapper;
    }

    private final MembroRepository membroRepository;
    private final MembroMapper membroMapper;


    public Membro cadastrarMembro(CreateMembroDto createMembroDto) throws IOException {
        boolean duplicado = membroRepository.ExistisDuplicado(createMembroDto.getCpf(), createMembroDto.getEmail(), createMembroDto.getTelefone());

       if (duplicado) {
           throw new MembroException("Membro já cadastrado com este CPF, E-mail ou telefone informado");
       }
        Membro membro = membroMapper.toEntity(createMembroDto);
        membro.setDataCadastro(LocalDate.now());
        membro.setStatus(StatusMembro.ATIVO);

        salvarImagem(createMembroDto, membro);

        return membroRepository.save(membro);
    }

    public void atualizarMembro(@NotNull CreateMembroDto createMembroDto){
        if (!membroRepository.existsByCpf(createMembroDto.getCpf())) {
            throw new MembroException("Membro não encontrado para atualização");
        }else {
            membroRepository.save(membroMapper.toEntity(createMembroDto));
        }
    }

    public Membro buscarMembroById(UUID id){
       return  membroRepository.findById(id)
               .orElseThrow(()-> new MembroException("Membro não encontrado"));
    }

    public List<Membro> BuscarPorNome(String nome){
        List<Membro> membro = membroRepository.buscarPorNome(nome);
        return membro;
    }

    public List<CreateMembroDto> listarMembros(String cargo, String ministerio, StatusMembro status) {
        List<Membro> membros = membroRepository.filtrar(cargo != null ? cargo.trim().toLowerCase() : null,
                ministerio != null ? ministerio.trim().toLowerCase() : null, status);
         MostrarMembrosDeletados();
        return membroMapper.toDtoList(membros);
    }

    public void alterarStatus(UUID id){
        Membro membro = membroRepository.findById(id).orElseThrow(()-> new RuntimeException("Membro não encontrado"));

       if (membro.getStatus() == StatusMembro.INATIVO){
           membro.setStatus(StatusMembro.ATIVO);
       }else {
           membro.setStatus((StatusMembro.INATIVO));
       }
        membroRepository.save(membro);
    }

    public void deletarMembro(UUID id){
        Membro membro = membroRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Membro não encontrado"));
        membrosDeletados.add(membro);
        membroRepository.deleteById(id);

    }

   private void MostrarMembrosDeletados(){
        membrosDeletados.forEach(m -> {
            System.out.println(m.getNomeCompleto());
        });
    }


    void salvarImagem(CreateMembroDto dto, Membro membro) throws IOException {
        if (dto.getImagem() != null) {


            if (dto.getImagem().getBase64() != null && !dto.getImagem().getBase64().isBlank()) {

                // salvar base64

                Imagem img = imagemService.salvarBase64(dto.getImagem().getBase64());
                membro.setImagem(img);

            } else if (dto.getImagem().getId() != null) {

                // imagem já existe no banco
                Imagem img = imagemRepository.findById(dto.getImagem().getId())
                        .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));
                membro.setImagem(img);
            }
        }


    }
}




