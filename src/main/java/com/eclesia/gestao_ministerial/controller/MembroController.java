package com.eclesia.gestao_ministerial.controller;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.mapper.MembroMapper;
import com.eclesia.gestao_ministerial.model.Membro;
import com.eclesia.gestao_ministerial.service.MembroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/membros")

public class MembroController {

    public MembroController(MembroService membroService, MembroMapper membroMapper) {
        this.membroService = membroService;
        this.membroMapper = membroMapper;
    }

    private final MembroService membroService;
    private final MembroMapper membroMapper;

    @PostMapping("/cadastrar")
    public ResponseEntity<Membro>CadastrarMembro(@RequestBody @Valid CreateMembroDto membroDto){
        membroService.cadastrarMembro(membroDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand().toUri() ).body(membroMapper.toEntity(membroDto));
    }

    @PutMapping("/atualizar")
        public ResponseEntity<Membro>AtualizarMembro(@RequestBody @Valid CreateMembroDto membroDto){
        membroService.atualizarMembro(membroDto);
        return ResponseEntity.ok(membroMapper.toEntity(membroDto));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Membro>buscarMembro( @PathVariable UUID id){
        membroService.buscarMembroById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Membro> listarMembros(){
        membroService.listarMembros();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void>deletarMembro(@PathVariable UUID id){
        membroService.deletarMembro(id);
        return ResponseEntity.noContent().build();
    }

}
