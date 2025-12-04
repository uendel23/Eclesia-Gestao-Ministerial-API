package com.eclesia.gestao_ministerial.controller;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.DTO.MembroFiltroDto;
import com.eclesia.gestao_ministerial.DTO.ResponseMembroDto;
import com.eclesia.gestao_ministerial.enums.StatusMembro;
import com.eclesia.gestao_ministerial.mapper.MembroMapper;
import com.eclesia.gestao_ministerial.model.Membro;
import com.eclesia.gestao_ministerial.service.MembroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
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
    public ResponseEntity<ResponseMembroDto>CadastrarMembro(@RequestBody @Valid CreateMembroDto createMembroDto) throws IOException {
        Membro membroSalvo = membroService.cadastrarMembro(createMembroDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(membroSalvo.getId()).toUri() ).body(membroMapper.createMembroDtoToResposnseDto(createMembroDto));
    }

    @PutMapping("/atualizar/{id}")
        public ResponseEntity<ResponseMembroDto> AtualizarMembroId(@RequestBody @Valid CreateMembroDto createMembroDto, @PathVariable UUID id){
        membroService.atualizarMembro(createMembroDto);
        return ResponseEntity.ok(membroMapper.createMembroDtoToResposnseDto(createMembroDto));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ResponseMembroDto> buscarMembroId(@PathVariable UUID id){
       Membro membro = membroService.buscarMembroById(id);
        return ResponseEntity.ok(membroMapper.membroToResponseMembroDto(membro));
    }

    @GetMapping("/buscarNome/{nome}")
    public ResponseEntity<List<ResponseMembroDto>> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(membroMapper.ResponseMembroDtotoMembroList(membroService.BuscarPorNome(nome)));
    }

    @GetMapping("/listar/filtro")
    public ResponseEntity<List<ResponseMembroDto>> listarMembros(MembroFiltroDto filtro){
        List<CreateMembroDto> membros = membroService.listarMembros(filtro.getCargo(), filtro.getMinisterio(), filtro.getStatus());
        return ResponseEntity.ok(membroMapper.CreateMembroDtotoResposnseMembroDtoList(membros));
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity alterarStatus(@PathVariable UUID id){
        membroService.alterarStatus(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/excluir/{id}")
    public  ResponseEntity<Void>deletar(@PathVariable UUID id){
        membroService.deletar(id);
        return  ResponseEntity.noContent().build();
    }


}
