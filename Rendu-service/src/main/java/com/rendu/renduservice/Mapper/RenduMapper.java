package com.rendu.renduservice.Mapper;

import com.rendu.renduservice.DTO.RenduDTO;
import com.rendu.renduservice.Model.Rendu;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RenduMapper {
    Rendu toEntity(RenduDTO dto);
    RenduDTO toDTO(Rendu rendu);
    List<RenduDTO> toDTOs(List<Rendu> renduList);
}
