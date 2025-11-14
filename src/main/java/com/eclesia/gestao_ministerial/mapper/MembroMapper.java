package com.eclesia.gestao_ministerial.mapper;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.model.Membro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembroMapper {

    MembroMapper Instance = Mappers.getMapper(MembroMapper.class);

    CreateMembroDto toDto(CreateMembroDto membroDto);
    Membro toEntity(CreateMembroDto membroDto);
    List<CreateMembroDto> toDtoList(List<Membro> membros);
    CreateMembroDto toDto(Membro membro);
}
