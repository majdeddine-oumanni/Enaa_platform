package com.brief.briefservice.Mapper;

import com.brief.briefservice.DTO.BriefDTO;
import com.brief.briefservice.Model.Brief;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BriefMapper {
    Brief toEntity(BriefDTO dto);
    BriefDTO toDTO(Brief brief);
    List<BriefDTO> toDTOs(List<Brief> briefs);
}
