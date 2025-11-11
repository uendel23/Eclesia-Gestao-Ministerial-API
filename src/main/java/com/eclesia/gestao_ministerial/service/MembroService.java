package com.eclesia.gestao_ministerial.service;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.mapper.MembroMapper;
import com.eclesia.gestao_ministerial.repository.MembroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class MembroService {

    public MembroService(MembroRepository membroRepository, MembroMapper membroMapper) {
        this.membroRepository = membroRepository;
        this.membroMapper = membroMapper;
    }

    private final MembroRepository membroRepository;
    private final MembroMapper membroMapper;

    public void cadastrarMembro(CreateMembroDto membroDto){
        //TUDO: Implementar lógica de validação e regras de negócio
        membroRepository.save(membroMapper.toEntity(membroDto));
    }

    public void atualizarMembro(CreateMembroDto membroDto){
        //TUDO: Implementar lógica de validação e regras de negócio
        membroRepository.save(membroMapper.toEntity(membroDto));
    }

    public void buscarMembroById(UUID id){
        //TUDO: Implementar lógica de validação e regras de negócio
        membroRepository.findById(id);
    }

    public void listarMembros() {
        //TUDO: Implementar lógica de listagem de membros
        membroRepository.findAll();
    }

    public void deletarMembro(UUID id){
        //TUDO: Implementar lógica de validação e regras de negócio
        membroRepository.deleteById(id);
    }




}
