package com.eclesia.gestao_ministerial.controller;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.mapper.MembroMapper;
import com.eclesia.gestao_ministerial.model.Membro;
import com.eclesia.gestao_ministerial.service.MembroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/membros")

public class MembroController {

    public MembroController(MembroService membroService, MembroMapper membroMapper) {
        this.membroService = membroService;
        this.membroMapper = membroMapper;
    }

    private final MembroService membroService;
    private final MembroMapper membroMapper;

    @PostMapping("/cadastrar")
    public ResponseEntity<Membro>CadastrarMembro(@RequestBody @Valid CreateMembroDto membroDto){
        Membro membroSalvo = membroService.cadastrarMembro(membroDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(membroSalvo.getId()).toUri() ).body(membroMapper.toEntity(membroDto));
    }

    @PutMapping("/atualizar/{id}")
        public ResponseEntity<Membro>AtualizarMembro(@RequestBody @Valid CreateMembroDto membroDto, @PathVariable UUID id){
        membroService.atualizarMembro(membroDto);
        return ResponseEntity.ok(membroMapper.toEntity(membroDto));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Membro>buscarMembro( @PathVariable UUID id){
       Membro membro = membroService.buscarMembroById(id);
        return ResponseEntity.ok(membro);
    }
    @GetMapping("/listar/filtro")
    public ResponseEntity<List<Membro>> listarMembros(  @RequestParam(required = false) String cargo,
                                                        @RequestParam(required = false) String ministerio,
                                                        @RequestParam(required = false) Boolean ativo){
        List<CreateMembroDto> membros = membroService.listarMembros(cargo, ministerio, ativo);
        return ResponseEntity.ok(membroMapper.toMembroList(membros));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void>deletarMembro(@PathVariable UUID id){
        membroService.deletarMembro(id);
        return ResponseEntity.noContent().build();
    }

}
