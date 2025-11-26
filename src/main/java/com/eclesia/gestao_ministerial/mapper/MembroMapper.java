package com.eclesia.gestao_ministerial.mapper;

import com.eclesia.gestao_ministerial.DTO.CreateMembroDto;
import com.eclesia.gestao_ministerial.DTO.ImagemDto;
import com.eclesia.gestao_ministerial.DTO.ResponseMembroDto;
import com.eclesia.gestao_ministerial.model.Imagem;
import com.eclesia.gestao_ministerial.model.Membro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembroMapper {

    MembroMapper Instance = Mappers.getMapper(MembroMapper.class);


    @Mapping(target = "imagem", expression = "java(mapImagem(membro.getImagem()))")
    CreateMembroDto toDto(Membro membro);
    default ImagemDto mapImagem(Imagem imagem) {
        if (imagem == null) return null;

        ImagemDto dto = new ImagemDto();
        dto.setId(imagem.getId());
        dto.setBase64(imagem.getBase64());
        return dto;
    }
    Membro toEntity(CreateMembroDto createMembroDto);
    List<ResponseMembroDto> ResponseMembroDtotoMembroList(List<Membro> membros);
    List<CreateMembroDto> toDtoList(List<Membro> membros);
    List<Membro> toMembroList(List<CreateMembroDto> membros);
    ResponseMembroDto membroToResponseMembroDto(Membro membro);
    ResponseMembroDto createMembroDtoToResposnseDto(CreateMembroDto createMembroDto);
    List<ResponseMembroDto> CreateMembroDtotoResposnseMembroDtoList(List<CreateMembroDto> membros);
}
