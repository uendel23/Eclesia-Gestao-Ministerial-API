package com.eclesia.gestao_ministerial.service;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.exception.MembroException;
import com.eclesia.gestao_ministerial.mapper.MembroMapper;
import com.eclesia.gestao_ministerial.model.Membro;
import com.eclesia.gestao_ministerial.repository.MembroRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MembroService {

  private List<Membro> membrosDeletados = new ArrayList<>();

    public MembroService(MembroRepository membroRepository, MembroMapper membroMapper) {
        this.membroRepository = membroRepository;
        this.membroMapper = membroMapper;
    }

    private final MembroRepository membroRepository;
    private final MembroMapper membroMapper;

    public Membro cadastrarMembro(CreateMembroDto membroDto){
       if (!membroRepository.existsByCpf(membroDto.getCpf()) &&
               !membroRepository.existsByEmail(membroDto.getEmail()) &&
               !membroRepository.existsByTelefone(membroDto.getTelefone())) {
          return membroRepository.save(membroMapper.toEntity(membroDto));
       }else {
           throw new MembroException("Membro já cadastrado");
        }
    }

    public void atualizarMembro(CreateMembroDto membroDto){
        if (!membroRepository.existsByCpf(membroDto.getCpf())) {
            throw new MembroException("Membro não encontrado para atualização");
        }else {
            membroRepository.save(membroMapper.toEntity(membroDto));
        }
    }

    public Membro buscarMembroById(UUID id){
       return  membroRepository.findById(id)
               .orElseThrow(()-> new MembroException("Membro não encontrado"));
    }

    public List<CreateMembroDto> listarMembros(String cargo,String ministerio,Boolean ativo) {
        List<Membro> membros = membroRepository.filtrar(cargo != null ? cargo.trim().toLowerCase() : null,
                ministerio != null ? ministerio.trim().toLowerCase() : null,
                ativo);
        return membroMapper.toDtoList(membros);
    }

    public void deletarMembro(UUID id){
        Membro membro = membroRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Membro não encontrado"));
        membrosDeletados = List.of(membro);
    }
}
