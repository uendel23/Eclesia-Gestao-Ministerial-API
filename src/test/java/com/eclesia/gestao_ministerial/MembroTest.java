package com.eclesia.gestao_ministerial;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.enums.StatusMembro;
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

import java.io.IOException;
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
    StatusMembro statusMembro;
    @Mock
    private MembroMapper membroMapper;

    @InjectMocks
    private MembroService membroService;

    private CreateMembroDto createMembroDto;

    private Membro membro;

    @BeforeEach
    void setUp() {
        membroService = new MembroService(membroRepository, membroMapper);

        createMembroDto = new CreateMembroDto();
        createMembroDto.setCpf("123.456.789-00");

        membro = new Membro();
        membro.setCpf("123.456.789-00");
    }

    @Test
    void testCadastrarMembroComCpfNaoExistente() throws IOException {
        // Simula que o CPF ainda não existe
        when(membroRepository.existsByCpf("123.456.789-00")).thenReturn(false);
        when(membroMapper.toEntity(createMembroDto)).thenReturn(membro);

        membroService.cadastrarMembro(createMembroDto);

        verify(membroRepository).save(membro);
    }

    @Test
    void testCadastrarMembroComCpfExistenteDeveLancarExcecao() {
        // Simula que o CPF já existe
        when(membroRepository.existsByCpf("123.456.789-00")).thenReturn(true);

        assertThrows(MembroException.class, () -> membroService.cadastrarMembro(createMembroDto));
    }

    @Test
    void deveListarMembrosComFiltros(){
        String cargo = "pastor";
        String ministerio = "cede";

        Membro membro1 = new Membro();
        membro1.setId(UUID.randomUUID());
        membro1.setNomeCompleto("João Silva");
        membro1.setCargo("Pastor");
        membro1.setMinisterio("Louvor");
        membro1.setStatus(StatusMembro.ATIVO);

        Membro membro2 = new Membro();
        membro2.setId(UUID.randomUUID());
        membro2.setNomeCompleto("Maria Souza");
        membro2.setCargo("Pastor");
        membro2.setMinisterio("Louvor");
        membro2.setStatus(StatusMembro.ATIVO);



        List<Membro> membrosMock  = List.of(membro1, membro2);

        CreateMembroDto dto1 = new CreateMembroDto(membro1.getId(), membro1.getNomeCompleto(), membro1.getCargo(), membro1.getMinisterio(), membro1.getStatus());
        CreateMembroDto dto2 = new CreateMembroDto(membro2.getId(), membro2.getNomeCompleto(), membro2.getCargo(), membro2.getMinisterio(), membro2.getStatus());

        List<CreateMembroDto> dtosMock = List.of(dto1, dto2);


        when(membroRepository.filtrar(cargo, ministerio, StatusMembro.ATIVO)).thenReturn(membrosMock);
        when(membroMapper.toDtoList(membrosMock)).thenReturn(dtosMock);

        // Act
        List<CreateMembroDto> resultado = membroService.listarMembros(cargo, ministerio, StatusMembro.ATIVO);

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNomeCompleto());
        assertEquals("Maria Souza", resultado.get(1).getNomeCompleto());

        verify(membroRepository).filtrar(cargo, ministerio, StatusMembro.ATIVO);
        verify(membroMapper).toDtoList(membrosMock);

    }


}
