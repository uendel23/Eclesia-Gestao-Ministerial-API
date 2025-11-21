package com.eclesia.gestao_ministerial;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.exception.MembroException;
import com.eclesia.gestao_ministerial.mapper.MembroMapper;
import com.eclesia.gestao_ministerial.model.Membro;
import com.eclesia.gestao_ministerial.repository.MembroRepository;
import com.eclesia.gestao_ministerial.service.MembroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MembroTest {

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private MembroMapper membroMapper;

    @InjectMocks
    private MembroService membroService;

    private CreateMembroDto membroDto;

    private Membro membro;

    @BeforeEach
    void setUp() {
        membroService = new MembroService(membroRepository, membroMapper);

        membroDto = new CreateMembroDto();
        membroDto.setCpf("123.456.789-00");

        membro = new Membro();
        membro.setCpf("123.456.789-00");
    }

    @Test
    void testCadastrarMembroComCpfNaoExistente() {
        // Simula que o CPF ainda não existe
        when(membroRepository.existsByCpf("123.456.789-00")).thenReturn(false);
        when(membroMapper.toEntity(membroDto)).thenReturn(membro);

        membroService.cadastrarMembro(membroDto);

        verify(membroRepository).save(membro);
    }

    @Test
    void testCadastrarMembroComCpfExistenteDeveLancarExcecao() {
        // Simula que o CPF já existe
        when(membroRepository.existsByCpf("123.456.789-00")).thenReturn(true);

        assertThrows(MembroException.class, () -> membroService.cadastrarMembro(membroDto));
    }

    @Test
    void deveListarMembrosComFiltros(){
        String cargo = "pastor";
        String ministerio = "cede";
        boolean ativo = true;

        Membro membro1 = new Membro();
        membro1.setId(UUID.randomUUID());
        membro1.setNomeCompleto("João Silva");
        membro1.setCargo("Pastor");
        membro1.setMinisterio("Louvor");
        membro1.setAtivo(true);

        Membro membro2 = new Membro();
        membro2.setId(UUID.randomUUID());
        membro2.setNomeCompleto("Maria Souza");
        membro2.setCargo("Pastor");
        membro2.setMinisterio("Louvor");
        membro2.setAtivo(true);



        List<Membro> membrosMock  = List.of(membro1, membro2);

        CreateMembroDto dto1 = new CreateMembroDto(membro1.getId(), membro1.getNomeCompleto(), membro1.getCargo(), membro1.getMinisterio(), membro1.getAtivo());
        CreateMembroDto dto2 = new CreateMembroDto(membro2.getId(), membro2.getNomeCompleto(), membro2.getCargo(), membro2.getMinisterio(), membro2.getAtivo());

        List<CreateMembroDto> dtosMock = List.of(dto1, dto2);


        when(membroRepository.filtrar(cargo, ministerio, ativo)).thenReturn(membrosMock);
        when(membroMapper.toDtoList(membrosMock)).thenReturn(dtosMock);

        // Act
        List<CreateMembroDto> resultado = membroService.listarMembros(cargo, ministerio, ativo);

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNomeCompleto());
        assertEquals("Maria Souza", resultado.get(1).getNomeCompleto());

        verify(membroRepository).filtrar(cargo, ministerio, ativo);
        verify(membroMapper).toDtoList(membrosMock);

    }


}
