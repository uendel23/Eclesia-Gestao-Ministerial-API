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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MembroTest {

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private MembroMapper membroMapper;

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


}
